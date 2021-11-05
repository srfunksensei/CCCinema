package com.mb.service.impl.mapstruct;

import com.mb.converter.mapstruct.MapStructConverter;
import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.SeatDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.exception.ResourceNotFoundException;
import com.mb.models.Reservation;
import com.mb.models.Screening;
import com.mb.models.Seat;
import com.mb.models.SeatReserved;
import com.mb.repository.ReservationRepository;
import com.mb.repository.ScreeningRepository;
import com.mb.repository.SeatReservedRepository;
import com.mb.service.ISeatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service("seatServiceMapStruct")
public class SeatServiceMapStruct implements ISeatService {

    private final ScreeningRepository screeningRepo;
    private final ReservationRepository reservationRepo;
    private final SeatReservedRepository seatReservedRepo;
    private final MapStructConverter<Seat, SeatDto> converter;

    public SeatServiceMapStruct(final ScreeningRepository screeningRepo, final ReservationRepository reservationRepo,
                                  final SeatReservedRepository seatReservedRepo, @Qualifier("seatMapStructConverter") final MapStructConverter<Seat, SeatDto> converter) {
        this.screeningRepo = screeningRepo;
        this.reservationRepo = reservationRepo;
        this.seatReservedRepo = seatReservedRepo;
        this.converter = converter;
    }

    @Override
    @Transactional(readOnly = true)
    public ScreeningSeatsDto getSeats(final String screeningId) {
        final Screening screening = screeningRepo.findById(screeningId)
                .orElseThrow(ResourceNotFoundException::new);

        final Set<Seat> reservedSeats = screening.getReservedSeats();
        final Set<SeatDto> seats = screening.getSeats()
                .stream()
                .map(seat -> {
                    final SeatDto seatDto = converter.toDto(seat);
                    seatDto.setReserved(reservedSeats.contains(seat));
                    return seatDto;
                })
                .collect(Collectors.toSet());
        return new ScreeningSeatsDto(screeningId, seats);
    }

    @Override
    @Transactional
    public SeatReservationResultDto bookSeat(final String screeningId, final ReserveDto reservation) {
        final Screening screening = screeningRepo.findById(screeningId)
                .orElseThrow(ResourceNotFoundException::new);

        final Seat seatForReservation = getSeatForReservation(screening, reservation);
        if (!screening.getReservedSeats().contains(seatForReservation)) {
            final Reservation res = Reservation.builder()
                    .reserved(true)
                    .username(reservation.getUsername())
                    .screening(screening)
                    .build();

            reservationRepo.save(res);

            final SeatReserved sres = SeatReserved.builder()
                    .reservation(res)
                    .screening(screening)
                    .seat(seatForReservation)
                    .build();

            seatReservedRepo.save(sres);

            screening.addSeatReserved(sres);
            seatForReservation.addSeatReserved(sres);

            return SeatReservationResultDto.builder()
                    .screeningId(screeningId)
                    .success(true)
                    .message("Seat reserved successfully")
                    .build();
        }

        return SeatReservationResultDto.builder()
                .screeningId(screeningId)
                .success(false)
                .message("Seat already reserved")
                .build();
    }

    private Seat getSeatForReservation(final Screening screening, final ReserveDto reservation) {
        return screening.getSeats().stream()
                .filter(seat -> seat.getRow().equalsIgnoreCase(reservation.getRow()) && seat.getNum().equalsIgnoreCase(reservation.getNum()))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
    }
}

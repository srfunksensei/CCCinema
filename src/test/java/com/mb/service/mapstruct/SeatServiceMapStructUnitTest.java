package com.mb.service.mapstruct;

import com.mb.converter.mapstruct.SeatMapStructConverter;
import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.SeatDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.models.Reservation;
import com.mb.models.Screening;
import com.mb.models.Seat;
import com.mb.models.SeatReserved;
import com.mb.provider.ScreeningProvider;
import com.mb.repository.ReservationRepository;
import com.mb.repository.ScreeningRepository;
import com.mb.repository.SeatReservedRepository;
import com.mb.service.ISeatService;
import com.mb.service.impl.mapstruct.SeatServiceMapStruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class SeatServiceMapStructUnitTest {

    private final ScreeningRepository screeningRepository = Mockito.mock(ScreeningRepository.class);
    private final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private final SeatReservedRepository seatReservedRepository = Mockito.mock(SeatReservedRepository.class);
    private final SeatMapStructConverter seatMapStructConverter = Mockito.mock(SeatMapStructConverter.class);
    private ISeatService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SeatServiceMapStruct(screeningRepository, reservationRepository, seatReservedRepository, seatMapStructConverter);
    }

    @Test
    public void getSeats_noScreening() {
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> underTest.getSeats("not-existing-screening-id"));
    }

    @Test
    public void getSeats() {
        final int seatNum = 4;
        final Screening screening = ScreeningProvider.buildScreening(seatNum);
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.of(screening));

        for (final Seat seat : screening.getSeats()) {
            final SeatDto seatDto = SeatDto.builder()
                    .auditorium("auditorium")
                    .num(seat.getNum())
                    .row(seat.getRow())
                    .build();
            Mockito.when(seatMapStructConverter.toDto(eq(seat))).thenReturn(seatDto);
        }

        final ScreeningSeatsDto result = underTest.getSeats(screening.getId());
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertFalse(result.getSeats().isEmpty(), "Expected seats");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertEquals(seatNum, result.getSeats().size(), "Expected different seat number");
        for (final SeatDto seat : result.getSeats()) {
            Assertions.assertFalse(seat.isReserved(), "Expected different result");
        }
    }

    @Test
    public void bookSeat_noScreening() {
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.empty());

        final ReserveDto reservation =  ReserveDto.builder()
                .num("1")
                .row("0")
                .build();
        Assertions.assertThrows(NotFoundException.class, () -> underTest.bookSeat("not-existing-screening-id", reservation));
    }

    @Test
    public void bookSeat_alreadyReserved() {
        final int seatNum = 4;
        final Screening screening = ScreeningProvider.buildScreening(seatNum);
        final Reservation reservation = Reservation.builder()
                .screening(screening)
                .username("test")
                .reserved(true)
                .paid(true)
                .build();
        final Seat reservedSeat = screening.getSeats().stream().findAny().get();
        final SeatReserved seatReserved = SeatReserved.builder()
                .seat(reservedSeat)
                .screening(screening)
                .reservation(reservation)
                .build();
        reservation.addSeatReserved(seatReserved);
        screening.addReservations(reservation);
        screening.addSeatReserved(seatReserved);

        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.of(screening));

        final ReserveDto reservationDto =  ReserveDto.builder()
                .num(reservedSeat.getNum())
                .row(reservedSeat.getRow())
                .build();
        final SeatReservationResultDto result = underTest.bookSeat(screening.getId(), reservationDto);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertFalse(result.isSuccess(), "Expected seat already booked");
        Assertions.assertFalse(result.getMessage().isEmpty(), "Expected message");
    }

    @Test
    public void bookSeat() {
        final int seatNum = 4;
        final Screening screening = ScreeningProvider.buildScreening(seatNum);
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.of(screening));
        Mockito.when(reservationRepository.save(any())).thenReturn(Reservation.builder().build());
        Mockito.when(seatReservedRepository.save(any())).thenReturn(SeatReserved.builder().build());

        final ReserveDto reservation =  ReserveDto.builder()
                .num("1")
                .row("0")
                .build();
        final SeatReservationResultDto result = underTest.bookSeat(screening.getId(), reservation);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertTrue(result.isSuccess(), "Expected booked seat");
        Assertions.assertFalse(result.getMessage().isEmpty(), "Expected message");
    }
}

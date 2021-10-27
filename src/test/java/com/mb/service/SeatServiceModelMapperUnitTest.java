package com.mb.service;

import com.mb.converter.modelmapper.ModelMapperConverter;
import com.mb.converter.modelmapper.SeatModelMapperConverter;
import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningSeats;
import com.mb.dto.SeatDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.models.*;
import com.mb.repository.ReservationRepository;
import com.mb.repository.ScreeningRepository;
import com.mb.repository.SeatReservedRepository;
import com.mb.service.impl.SeatServiceModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;

public class SeatServiceModelMapperUnitTest {

    private final ScreeningRepository screeningRepository = Mockito.mock(ScreeningRepository.class);
    private final ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);
    private final SeatReservedRepository seatReservedRepository = Mockito.mock(SeatReservedRepository.class);
    private final ModelMapperConverter seatModelMapperConverter = Mockito.mock(SeatModelMapperConverter.class);
    private ISeatService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SeatServiceModelMapper(screeningRepository, reservationRepository, seatReservedRepository, seatModelMapperConverter);
    }

    @Test
    public void getSeats_noScreening() {
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> underTest.getSeats("not-existing-screening-id"));
    }

    @Test
    public void getSeats() {
        final int seatNum = 4;
        final Screening screening = buildScreening(seatNum);
        Mockito.when(screeningRepository.findById(anyString())).thenReturn(Optional.of(screening));

        for (final Seat seat : screening.getSeats()) {
            final SeatDto seatDto = SeatDto.builder()
                    .auditorium("auditorium")
                    .num(seat.getNum())
                    .row(seat.getRow())
                    .build();
            Mockito.when(seatModelMapperConverter.toDto(eq(seat), eq(SeatDto.class))).thenReturn(seatDto);
        }

        final ScreeningSeats result = underTest.getSeats(screening.getId());
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
        final Screening screening = buildScreening(seatNum);
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
        final Screening screening = buildScreening(seatNum);
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

    private Screening buildScreening(final int seatNum) {
        final int actualSeatNum = seatNum % 2 == 0 ? seatNum : seatNum - 1;
        final int colNum = 2;
        final int rowNum = actualSeatNum / colNum;

        final Set<Seat> seats = new HashSet<>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                final Seat seat = Seat.builder()
                        .row("" + i)
                        .num("" + j)
                        .build();
                seats.add(seat);
            }
        }

        final Movie movie = Movie.builder()
                .title("movie title")
                .director("movie director")
                .cast("movie cast")
                .description("movie description")
                .duration(120)
                .build();
        final Auditorium auditorium = Auditorium.builder()
                .name("auditorium name")
                .seatsNum(actualSeatNum)
                .build();
        auditorium.addSeats(seats);
        seats.forEach(s -> s.setAuditorium(auditorium));
        return Screening.builder()
                .id("default-id")
                .start(Timestamp.valueOf(LocalDateTime.now()))
                .movie(movie)
                .auditorium(auditorium)
                .build();
    }
}

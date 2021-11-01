package com.mb.service.modelmapper;

import com.mb.dto.ReserveDto;
import com.mb.dto.ScreeningSeatsDto;
import com.mb.dto.SeatDto;
import com.mb.dto.SeatReservationResultDto;
import com.mb.models.Screening;
import com.mb.models.Seat;
import com.mb.repository.ScreeningRepository;
import com.mb.service.AbstractIntegrationTest;
import com.mb.service.ISeatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;

public class SeatServiceModelMapperIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    @Qualifier("seatServiceModelMapper")
    private ISeatService underTest;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Test
    public void getSeats_noScreening() {
        Assertions.assertThrows(NotFoundException.class, () -> underTest.getSeats("not-existing-screening-id"));
    }

    @Test
    @Transactional
    public void getSeats() {
        final List<Screening> screnings = screeningRepository.findAll();
        Assertions.assertFalse(screnings.isEmpty(), "Expected to find some screenings");

        final Screening screening = screnings.get(0);

        final ScreeningSeatsDto result = underTest.getSeats(screening.getId());
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertFalse(result.getSeats().isEmpty(), "Expected seats");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertEquals(screening.getAuditorium().getSeats().size(), result.getSeats().size(), "Expected different seat number");
        for (final SeatDto seat : result.getSeats()) {
            Assertions.assertFalse(seat.isReserved(), "Expected different result");
        }
    }

    @Test
    public void bookSeat_noScreening() {
        final ReserveDto reservation =  ReserveDto.builder()
                .num("1")
                .row("0")
                .build();
        Assertions.assertThrows(NotFoundException.class, () -> underTest.bookSeat("not-existing-screening-id", reservation));
    }

    @Test
    @Transactional
    public void bookSeat_alreadyReserved() {
        final List<Screening> screnings = screeningRepository.findAll();
        Assertions.assertFalse(screnings.isEmpty(), "Expected to find some screenings");

        final Screening screening = screnings.get(0);

        final Set<Seat> seats = screening.getAuditorium().getSeats();
        Assertions.assertFalse(seats.isEmpty(), "Expected to find some seats in auditorium");
        final Seat seat = seats.iterator().next();

        final ReserveDto reservation =  ReserveDto.builder()
                .num(seat.getNum())
                .row(seat.getRow())
                .build();
        final SeatReservationResultDto result = underTest.bookSeat(screening.getId(), reservation);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertTrue(result.isSuccess(), "Expected booked seat");
        Assertions.assertFalse(result.getMessage().isEmpty(), "Expected message");

        final SeatReservationResultDto result1 = underTest.bookSeat(screening.getId(), reservation);
        Assertions.assertNotNull(result1, "Expected result");
        Assertions.assertEquals(screening.getId(), result1.getScreeningId(), "Expected different screening id");
        Assertions.assertFalse(result1.isSuccess(), "Expected already booked seat");
        Assertions.assertFalse(result1.getMessage().isEmpty(), "Expected message");
    }

    @Test
    @Transactional
    public void bookSeat() {
        final List<Screening> screnings = screeningRepository.findAll();
        Assertions.assertFalse(screnings.isEmpty(), "Expected to find some screenings");

        final Screening screening = screnings.get(0);

        final Set<Seat> seats = screening.getAuditorium().getSeats();
        Assertions.assertFalse(seats.isEmpty(), "Expected to find some seats in auditorium");
        final Seat seat = seats.iterator().next();

        final ReserveDto reservation =  ReserveDto.builder()
                .num(seat.getNum())
                .row(seat.getRow())
                .build();
        final SeatReservationResultDto result = underTest.bookSeat(screening.getId(), reservation);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different screening id");
        Assertions.assertTrue(result.isSuccess(), "Expected booked seat");
        Assertions.assertFalse(result.getMessage().isEmpty(), "Expected message");
    }
}

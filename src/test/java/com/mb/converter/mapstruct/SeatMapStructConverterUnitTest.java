package com.mb.converter.mapstruct;

import com.mb.dto.SeatDto;
import com.mb.models.Auditorium;
import com.mb.models.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeatMapStructConverterUnitTest {

    private final MapStructConverter<Seat, SeatDto> underTest = new SeatMapStructConverter();

    @Test
    public void mapSeat() {
        final Seat seat = buildSeat();

        final SeatDto result = underTest.toDto(seat);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(seat.getAuditorium().getName(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(seat.getNum(), result.getNum(), "Expected different value");
        Assertions.assertEquals(seat.getRow(), result.getRow(), "Expected different value");
        Assertions.assertFalse(result.isReserved(), "Expected default value");
    }

    @Test
    public void mapSeatList() {
        final Seat seat = buildSeat();
        final List<Seat> seats = Stream.of(seat).collect(Collectors.toList());

        final List<SeatDto> result = underTest.toDto(seats);
        Assertions.assertEquals(1, result.size(), "Expected different value");
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(seat.getAuditorium().getName(), result.get(0).getAuditorium(), "Expected different value");
        Assertions.assertEquals(seat.getNum(), result.get(0).getNum(), "Expected different value");
        Assertions.assertEquals(seat.getRow(), result.get(0).getRow(), "Expected different value");
        Assertions.assertFalse(result.get(0).isReserved(), "Expected default value");
    }

    private Seat buildSeat() {
        final Auditorium auditorium = Auditorium.builder()
                .name("auditorium name")
                .build();
        return Seat.builder()
                .row("row")
                .num("num")
                .auditorium(auditorium)
                .build();
    }
}

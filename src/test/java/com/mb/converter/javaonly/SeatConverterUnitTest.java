package com.mb.converter.javaonly;

import com.mb.dto.SeatDto;
import com.mb.models.Auditorium;
import com.mb.models.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeatConverterUnitTest {

    private final SeatConverter underTest = new SeatConverter();

    @Test
    public void mapSeat() {
        final Seat seat = buildSeat();

        final SeatDto result = underTest.convert(seat);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(seat.getAuditorium().getName(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(seat.getNum(), result.getNum(), "Expected different value");
        Assertions.assertEquals(seat.getRow(), result.getRow(), "Expected different value");
        Assertions.assertFalse(result.isReserved(), "Expected default value");
    }

    @Test
    public void mapSeat_null() {
        final SeatDto result = underTest.convert(null);
        Assertions.assertNull(result, "Expected null");
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

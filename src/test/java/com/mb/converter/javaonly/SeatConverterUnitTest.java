package com.mb.converter.javaonly;

import com.mb.dto.SeatDto;
import com.mb.models.Seat;
import com.mb.provider.SeatProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit-test")
public class SeatConverterUnitTest {

    private final SeatConverter underTest = new SeatConverter();

    @Test
    public void mapSeat() {
        final Seat seat = SeatProvider.buildSeat();

        final SeatDto result = underTest.convert(seat);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(seat.getAuditorium().getTitle(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(seat.getNum(), result.getNum(), "Expected different value");
        Assertions.assertEquals(seat.getRow(), result.getRow(), "Expected different value");
        Assertions.assertFalse(result.isReserved(), "Expected default value");
    }

    @Test
    public void mapSeat_null() {
        final SeatDto result = underTest.convert(null);
        Assertions.assertNull(result, "Expected null");
    }
}

package com.mb.converter.javaonly;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.provider.ScreeningProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit-test")
public class ScreeningConverterUnitTest {

    private final ScreeningConverter underTest = new ScreeningConverter();

    @Test
    public void mapScreening() {
        final Screening screening = ScreeningProvider.buildScreening();

        final ScreeningDto result = underTest.convert(screening);
        Assertions.assertEquals(screening.getAuditorium().getName(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getTitle(), result.getTitle(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDescription(), result.getDescription(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDirector(), result.getDirector(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDuration(), result.getDuration(), "Expected different value");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different value");
        Assertions.assertEquals(screening.getStart(), result.getStart(), "Expected different value");
    }

    @Test
    public void mapScreening_null() {
        final ScreeningDto result = underTest.convert(null);
        Assertions.assertNull(result, "Expected null");
    }
}

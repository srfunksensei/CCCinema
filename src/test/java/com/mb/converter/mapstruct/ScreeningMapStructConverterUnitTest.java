package com.mb.converter.mapstruct;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.provider.ScreeningProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Tag("unit-test")
public class ScreeningMapStructConverterUnitTest {

    private final MapStructConverter<Screening, ScreeningDto> underTest = new ScreeningMapStructConverter();

    @Test
    public void mapScreening() {
        final Screening screening = ScreeningProvider.buildScreening();

        final ScreeningDto result = underTest.toDto(screening);
        Assertions.assertEquals(screening.getAuditorium().getTitle(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getTitle(), result.getTitle(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDescription(), result.getDescription(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDirector(), result.getDirector(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDuration(), result.getDuration(), "Expected different value");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different value");
        Assertions.assertEquals(screening.getStartingTime(), result.getStartingTime(), "Expected different value");
    }

    @Test
    public void mapScreeningList() {
        final Screening screening = ScreeningProvider.buildScreening();
        final List<Screening> screenings = Stream.of(screening).collect(Collectors.toList());

        final List<ScreeningDto> result = underTest.toDto(screenings);
        Assertions.assertEquals(1, result.size(), "Expected different value");
        Assertions.assertEquals(screening.getAuditorium().getTitle(), result.get(0).getAuditorium(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getTitle(), result.get(0).getTitle(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDescription(), result.get(0).getDescription(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDirector(), result.get(0).getDirector(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDuration(), result.get(0).getDuration(), "Expected different value");
        Assertions.assertEquals(screening.getId(), result.get(0).getScreeningId(), "Expected different value");
        Assertions.assertEquals(screening.getStartingTime(), result.get(0).getStartingTime(), "Expected different value");
    }
}

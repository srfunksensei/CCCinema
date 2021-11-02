package com.mb.converter.javaonly;

import com.mb.dto.ScreeningDto;
import com.mb.models.Auditorium;
import com.mb.models.Movie;
import com.mb.models.Screening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScreeningConverterUnitTest {

    private final ScreeningConverter underTest = new ScreeningConverter();

    @Test
    public void mapScreening() {
        final Screening screening = buildScreening();

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

    private Screening buildScreening() {
        final Movie movie = Movie.builder()
                .title("movie title")
                .director("movie director")
                .cast("movie cast")
                .description("movie description")
                .duration(120)
                .build();
        final Auditorium auditorium = Auditorium.builder()
                .name("auditorium name")
                .build();
        return Screening.builder()
                .id("default-id")
                .start(Timestamp.valueOf(LocalDateTime.now()))
                .movie(movie)
                .auditorium(auditorium)
                .build();
    }
}

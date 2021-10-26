package com.mb.converter;

import com.mb.dto.ScreeningDto;
import com.mb.dto.SeatDto;
import com.mb.models.Auditorium;
import com.mb.models.Movie;
import com.mb.models.Screening;
import com.mb.models.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelMapperConverterUnitTest {

    private final ModelMapperConverter underTest = new ModelMapperConverter();

    @Test
    public void mapScreening() {
        final Screening screening = buildScreening();

        final ScreeningDto result = underTest.toDto(screening, ScreeningDto.class);
        Assertions.assertEquals(screening.getAuditorium().getName(), result.getAuditorium(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getTitle(), result.getTitle(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDescription(), result.getDescription(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDirector(), result.getDirector(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDuration(), result.getDuration(), "Expected different value");
        Assertions.assertEquals(screening.getId(), result.getScreeningId(), "Expected different value");
        Assertions.assertEquals(screening.getStart(), result.getStart(), "Expected different value");
    }

    @Test
    public void mapScreeningList() {
        final Screening screening = buildScreening();
        final List<Screening> screenings = Stream.of(screening).collect(Collectors.toList());

        final List<ScreeningDto> result = underTest.toDto(screenings, ScreeningDto.class);
        Assertions.assertEquals(1, result.size(), "Expected different value");
        Assertions.assertEquals(screening.getAuditorium().getName(), result.get(0).getAuditorium(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getTitle(), result.get(0).getTitle(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDescription(), result.get(0).getDescription(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDirector(), result.get(0).getDirector(), "Expected different value");
        Assertions.assertEquals(screening.getMovie().getDuration(), result.get(0).getDuration(), "Expected different value");
        Assertions.assertEquals(screening.getId(), result.get(0).getScreeningId(), "Expected different value");
        Assertions.assertEquals(screening.getStart(), result.get(0).getStart(), "Expected different value");
    }

    @Test
    public void mapSeat() {
        final Seat seat = buildSeat();

        final SeatDto result = underTest.toDto(seat, SeatDto.class);
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

        final List<SeatDto> result = underTest.toDto(seats, SeatDto.class);
        Assertions.assertEquals(1, result.size(), "Expected different value");
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(seat.getAuditorium().getName(), result.get(0).getAuditorium(), "Expected different value");
        Assertions.assertEquals(seat.getNum(), result.get(0).getNum(), "Expected different value");
        Assertions.assertEquals(seat.getRow(), result.get(0).getRow(), "Expected different value");
        Assertions.assertFalse(result.get(0).isReserved(), "Expected default value");
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

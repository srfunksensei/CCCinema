package com.mb.provider;

import com.mb.models.Auditorium;
import com.mb.models.Movie;
import com.mb.models.Screening;
import com.mb.models.Seat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ScreeningProvider {

    public static Screening buildScreening() {
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

    public static Screening buildScreening(final int seatNum) {
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

        final Screening screening = buildScreening();
        final Auditorium auditorium = screening.getAuditorium();
        auditorium.addSeats(seats);
        seats.forEach(s -> s.setAuditorium(auditorium));

        return screening;
    }
}

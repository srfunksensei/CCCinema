package com.mb.provider;

import com.mb.models.Auditorium;
import com.mb.models.Seat;

public class SeatProvider {

    public static Seat buildSeat() {
        final Auditorium auditorium = Auditorium.builder()
                .title("auditorium title")
                .build();
        return Seat.builder()
                .row("row")
                .num("num")
                .auditorium(auditorium)
                .build();
    }
}

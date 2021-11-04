package com.mb.provider;

import com.mb.models.Auditorium;
import com.mb.models.Seat;

public class SeatProvider {

    public static Seat buildSeat() {
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

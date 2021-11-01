package com.mb.converter.mapstruct;

import com.mb.dto.SeatDto;
import com.mb.models.Seat;

public class SeatMapStructConverter implements MapStructConverter<Seat, SeatDto> {

    @Override
    public SeatDto toDto(final Seat source) {
        if (source == null) {
            return null;
        }

        return SeatDto.builder()
                .auditorium(source.getAuditorium().getName())
                .row(source.getRow())
                .num(source.getNum())
                .build();
    }
}

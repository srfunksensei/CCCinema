package com.mb.converter.javaonly;

import com.mb.dto.SeatDto;
import com.mb.models.Seat;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SeatConverter implements Converter<Seat, SeatDto> {

    @Override
    public SeatDto convert(final Seat source) {
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

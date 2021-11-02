package com.mb.converter.javaonly;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScreeningConverter implements Converter<Screening, ScreeningDto> {

    @Override
    public ScreeningDto convert(final Screening source) {
        if (source == null) {
            return null;
        }

        return ScreeningDto.builder()
                .auditorium(source.getAuditorium().getName())
                .title(source.getMovie().getTitle())
                .description(source.getMovie().getDescription())
                .director(source.getMovie().getDirector())
                .duration(source.getMovie().getDuration())
                .screeningId(source.getId())
                .start(source.getStart())
                .build();
    }
}

package com.mb.converter.mapstruct;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import org.springframework.stereotype.Component;

@Component(value = "screeningMapStructConverter")
public class ScreeningMapStructConverter implements MapStructConverter<Screening, ScreeningDto> {

    @Override
    public  ScreeningDto toDto(final Screening source) {
        if (source == null) {
            return null;
        }

        return ScreeningDto.builder()
                .auditorium(source.getAuditorium().getTitle())
                .title(source.getMovie().getTitle())
                .description(source.getMovie().getDescription())
                .director(source.getMovie().getDirector())
                .duration(source.getMovie().getDuration())
                .screeningId(source.getId())
                .startingTime(source.getStartingTime())
                .build();
    }
}

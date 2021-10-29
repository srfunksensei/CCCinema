package com.mb.converter.mapstruct;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;

import java.util.List;
import java.util.stream.Collectors;

public class ScreeningMapStructConverter implements MapStructConverter<Screening, ScreeningDto> {

    @Override
    public  ScreeningDto toDto(final Screening source) {
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

    @Override
    public List<ScreeningDto> toDto(final List<Screening> source) {
        if (source == null) {
            return null;
        }

        return source
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

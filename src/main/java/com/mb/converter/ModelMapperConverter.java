package com.mb.converter;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class ModelMapperConverter {

    private static final ModelMapper MAPPER = new ModelMapper();
    private static final PropertyMap<Screening, ScreeningDto> SCREENING_MAP = new PropertyMap<Screening, ScreeningDto>() {
        protected void configure() {
            map().setAuditorium(source.getAuditorium().getName());
            map().setTitle(source.getMovie().getTitle());
            map().setDescription(source.getMovie().getDescription());
            map().setDirector(source.getMovie().getDirector());
            map().setDuration(source.getMovie().getDuration());
            map().setScreeningId(source.getId());
        }
    };
    static {
        MAPPER.addMappings(SCREENING_MAP);
    }

    public <S, T> T toDto(final S source, final Class<T> targetClass) {
        return MAPPER.map(source, targetClass);
    }

    public <S, T> List<T> toDto(final List<S> source, final Class<T> targetClass) {
        return source
                .stream()
                .map(element -> toDto(element, targetClass))
                .collect(Collectors.toList());
    }
}

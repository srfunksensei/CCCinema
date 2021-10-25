package com.mb.converter;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

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

    public List<ScreeningDto> toDto(final List<Screening> screenings) {
        final Type listType = new TypeToken<List<ScreeningDto>>() {}.getType();
        return MAPPER.map(screenings, listType);
    }

    public ScreeningDto toDto(final Screening screening) {
        return MAPPER.map(screening, ScreeningDto.class);
    }
}

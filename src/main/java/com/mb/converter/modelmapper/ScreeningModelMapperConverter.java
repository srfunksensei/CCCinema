package com.mb.converter.modelmapper;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component(value = "screeningModelMapperConverter")
public class ScreeningModelMapperConverter extends ModelMapperConverter {

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

    public ScreeningModelMapperConverter() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(SCREENING_MAP);
    }
}

package com.mb.converter.modelmapper;

import com.mb.dto.SeatDto;
import com.mb.models.Seat;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component(value = "seatModelMapperConverter")
public class SeatModelMapperConverter extends ModelMapperConverter {

    public static final PropertyMap<Seat, SeatDto> SEAT_MAP = new PropertyMap<>() {
        protected void configure() {
            map().setAuditorium(source.getAuditorium().getTitle());
            map().setNum(source.getNum());
            map().setRow(source.getRow());
            skip().setReserved(false);
        }
    };

    public SeatModelMapperConverter() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(SEAT_MAP);
    }
}

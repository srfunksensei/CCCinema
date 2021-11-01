package com.mb.service.impl.mapstruct;

import com.mb.converter.mapstruct.MapStructConverter;
import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.IScreeningService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("screeningServiceMapStruct")
public class ScreeningServiceMapStruct implements IScreeningService {

    private final ScreeningRepository screeningRepo;
    private final MapStructConverter<Screening, ScreeningDto> converter;

    public ScreeningServiceMapStruct(final ScreeningRepository screeningRepo, @Qualifier("screeningMapStructConverter") final MapStructConverter<Screening, ScreeningDto> converter) {
        this.screeningRepo = screeningRepo;
        this.converter = converter;
    }

    @Override
    public List<ScreeningDto> getUpcoming(final Timestamp from) {
        return converter.toDto(screeningRepo.findByStartAfter(from));
    }
}

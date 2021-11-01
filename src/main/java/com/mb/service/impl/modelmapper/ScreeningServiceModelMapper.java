package com.mb.service.impl.modelmapper;

import com.mb.converter.modelmapper.ModelMapperConverter;
import com.mb.dto.ScreeningDto;
import com.mb.repository.ScreeningRepository;
import com.mb.service.IScreeningService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("screeningServiceModelMapper")
public class ScreeningServiceModelMapper implements IScreeningService {

	private final ScreeningRepository screeningRepo;
	private final ModelMapperConverter converter;

	public ScreeningServiceModelMapper(final ScreeningRepository screeningRepo, @Qualifier("screeningModelMapperConverter") final ModelMapperConverter converter) {
		this.screeningRepo = screeningRepo;
		this.converter = converter;
	}

	@Override
	public List<ScreeningDto> getUpcoming(final Timestamp from) {
		return converter.toDto(screeningRepo.findByStartAfter(from), ScreeningDto.class);
	}
}

package com.mb.service.impl;

import com.mb.converter.modelmapper.ModelMapperConverter;
import com.mb.dto.ScreeningDto;
import com.mb.repository.ScreeningRepository;
import com.mb.service.IScreeningService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ScreeningServiceModelMapper implements IScreeningService {

	private final ScreeningRepository screeningRepo;
	private final ModelMapperConverter converter;

	@Override
	public List<ScreeningDto> getUpcoming(final Timestamp from) {
		return converter.toDto(screeningRepo.findByStartAfter(from), ScreeningDto.class);
	}
}

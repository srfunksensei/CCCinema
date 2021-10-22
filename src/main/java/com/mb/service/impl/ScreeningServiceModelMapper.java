package com.mb.service.impl;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.IScreeningService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ScreeningServiceModelMapper implements IScreeningService {

	private final ScreeningRepository screeningRepo;
	
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


	@Override
	public List<ScreeningDto> getUpcoming(final Timestamp from) {
		final Type listType = new TypeToken<List<ScreeningDto>>() {}.getType();
		return MAPPER.map(screeningRepo.findByStartAfter(from), listType);
	}
}

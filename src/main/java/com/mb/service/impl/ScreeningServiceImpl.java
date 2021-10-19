package com.mb.service.impl;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.ScreeningService;

@Service
public class ScreeningServiceImpl implements ScreeningService {

	@Autowired
	private ScreeningRepository screeningRepo;
	
	private static ModelMapper MAPPER = new ModelMapper();
	
	PropertyMap<Screening, ScreeningDto> SCREENING_MAP = new PropertyMap<Screening, ScreeningDto>() {
		protected void configure() {
			map().setAuditorium(source.getAuditorium().getName());
			
			map().setTitle(source.getMovie().getTitle());
			map().setDescription(source.getMovie().getDescription());
			map().setDirector(source.getMovie().getDirector());
			map().setDuration(source.getMovie().getDuration());
			
			map().setScreeningId(source.getId());
		}
	};

	public ScreeningServiceImpl() {
		MAPPER.addMappings(SCREENING_MAP);
	}
	
	@Override
	public List<ScreeningDto> getUpcoming(Date from) {
		Type listType = new TypeToken<List<ScreeningDto>>() {}.getType();
		return MAPPER.map(screeningRepo.findByStartAfter(from), listType);
	}

	public ScreeningRepository getScreeningRepo() {
		return screeningRepo;
	}

	public void setScreeningRepo(ScreeningRepository screeningRepo) {
		this.screeningRepo = screeningRepo;
	}
}

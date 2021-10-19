package com.mb.service;

import java.util.Date;
import java.util.List;

import com.mb.dto.ScreeningDto;

public interface ScreeningService {

	List<ScreeningDto> getUpcoming(Date from);
}

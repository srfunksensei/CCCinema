package com.mb.service;

import com.mb.dto.ScreeningDto;

import java.sql.Timestamp;
import java.util.List;

public interface IScreeningService {

	List<ScreeningDto> getUpcoming(final Timestamp from);
}

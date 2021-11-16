package com.mb.controller.v2;

import com.mb.api.version.APIPath;
import com.mb.controller.CinemaController;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = APIPath.API + APIPath.VERSION_2 + APIPath.SCREENING)
public class CinemaControllerV2 extends CinemaController {

	public CinemaControllerV2(@Qualifier("screeningServiceMapStruct") final IScreeningService screeningService,
							  @Qualifier("seatServiceMapStruct") final ISeatService seatService) {
		this.screeningService = screeningService;
		this.seatService = seatService;
	}
}

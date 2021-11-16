package com.mb.controller.v1;

import com.mb.api.version.APIPath;
import com.mb.controller.CinemaController;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = APIPath.API + APIPath.VERSION_1 + APIPath.SCREENING)
public class CinemaControllerV1 extends CinemaController {

	public CinemaControllerV1(@Qualifier("screeningServiceModelMapper") final IScreeningService screeningService,
                              @Qualifier("seatServiceModelMapper") final ISeatService seatService) {
		this.screeningService = screeningService;
		this.seatService = seatService;
	}
}

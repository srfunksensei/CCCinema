package com.mb.controller.v1;

import com.mb.api.version.APIPath;
import com.mb.controller.CinemaControllerUnitTest;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Tag("unit-test")
@WebMvcTest(controllers = CinemaControllerV1.class)
public class CinemaControllerV1UnitTest extends CinemaControllerUnitTest {

    private static final String ROOT_PATH_V1 = APIPath.API + APIPath.VERSION_1 + APIPath.SCREENING;

    @MockBean
    @Qualifier("screeningServiceModelMapper")
    private IScreeningService screeningService;

    @MockBean
    @Qualifier("seatServiceModelMapper")
    private ISeatService seatService;

    @Override
    protected String getRootPath() {
        return ROOT_PATH_V1;
    }

    @Override
    protected IScreeningService getScreeningService() {
        return screeningService;
    }

    @Override
    protected ISeatService getSeatService() {
        return seatService;
    }
}

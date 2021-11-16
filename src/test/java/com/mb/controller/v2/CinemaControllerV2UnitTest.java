package com.mb.controller.v2;

import com.mb.api.version.APIPath;
import com.mb.controller.CinemaControllerUnitTest;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Tag("unit-test")
@WebMvcTest(controllers = CinemaControllerV2.class)
public class CinemaControllerV2UnitTest extends CinemaControllerUnitTest {

    private static final String ROOT_PATH_V2 = APIPath.API + APIPath.VERSION_2 + APIPath.SCREENING;

    @MockBean
    @Qualifier("screeningServiceMapStruct")
    private IScreeningService screeningService;

    @MockBean
    @Qualifier("seatServiceMapStruct")
    private ISeatService seatService;

    @Override
    protected String getRootPath() {
        return ROOT_PATH_V2;
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

package com.mb.service.mapstruct;

import com.mb.converter.mapstruct.ScreeningMapStructConverter;
import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.IScreeningService;
import com.mb.service.impl.mapstruct.ScreeningServiceMapStruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Tag("unit-test")
public class ScreeningServiceMapStructUnitTest {

    private IScreeningService underTest;
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        final ScreeningMapStructConverter screeningMapStructConverter = Mockito.mock(ScreeningMapStructConverter.class);
        underTest = new ScreeningServiceMapStruct(screeningRepository, screeningMapStructConverter);
    }

    @Test
    public void getUpcoming() {
        final ArrayList<Screening> toReturn = new ArrayList<>();
        when(screeningRepository.findByStartingTimeAfter(any(Timestamp.class))).thenReturn(toReturn);

        final List<ScreeningDto> result = underTest.getUpcoming(new Timestamp(System.currentTimeMillis()));
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(toReturn.size(), result.size(), "Expected different size");
    }
}

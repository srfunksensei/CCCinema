package com.mb.service;

import com.mb.converter.modelmapper.ScreeningModelMapperConverter;
import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.impl.ScreeningServiceModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ScreeningServiceModelMapperUnitTest {

    private IScreeningService underTest;
    private ScreeningRepository screeningRepository;
    private ScreeningModelMapperConverter screeningModelMapperConverter;

    @BeforeEach
    public void setUp() {
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        screeningModelMapperConverter = Mockito.mock(ScreeningModelMapperConverter.class);
        underTest = new ScreeningServiceModelMapper(screeningRepository, screeningModelMapperConverter);
    }

    @Test
    public void getUpcoming() {
        final ArrayList<Screening> toReturn = new ArrayList<>();
        when(screeningRepository.findByStartAfter(any(Timestamp.class))).thenReturn(toReturn);

        final List<ScreeningDto> result = underTest.getUpcoming(new Timestamp(System.currentTimeMillis()));
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(toReturn.size(), result.size(), "Expected different size");
    }
}

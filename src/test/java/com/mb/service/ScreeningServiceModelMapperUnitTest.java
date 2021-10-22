package com.mb.service;

import com.mb.dto.ScreeningDto;
import com.mb.models.Screening;
import com.mb.repository.ScreeningRepository;
import com.mb.service.impl.ScreeningServiceModelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ScreeningServiceModelMapperUnitTest {

    private IScreeningService underTest;
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        screeningRepository = Mockito.mock(ScreeningRepository.class);
        underTest = new ScreeningServiceModelMapper(screeningRepository);
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

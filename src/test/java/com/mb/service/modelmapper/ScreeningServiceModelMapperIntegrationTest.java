package com.mb.service.modelmapper;

import com.mb.dto.ScreeningDto;
import com.mb.service.AbstractIntegrationTest;
import com.mb.service.IScreeningService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public class ScreeningServiceModelMapperIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    @Qualifier("screeningServiceModelMapper")
    private IScreeningService underTest;

    @Test
    public void getUpcoming_nothing() {
        final Timestamp from = Timestamp.from(
                LocalDate.now().plusMonths(1).atStartOfDay().toInstant(ZoneOffset.UTC)
        );

        final List<ScreeningDto> result = underTest.getUpcoming(from);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(0, result.size(), "Expected different size");
    }

    @Test
    public void getUpcoming_filtered() {
        final Timestamp from = Timestamp.from(
                LocalDate.now().atStartOfDay().plusDays(1).toInstant(ZoneOffset.UTC)
        );

        final List<ScreeningDto> result = underTest.getUpcoming(from);
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(6, result.size(), "Expected different size");
    }
}

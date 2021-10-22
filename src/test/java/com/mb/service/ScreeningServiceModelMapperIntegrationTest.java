package com.mb.service;

import com.mb.dto.ScreeningDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Sql("classpath:testdata.sql")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScreeningServiceModelMapperIntegrationTest {

    @Autowired
    private IScreeningService underTest;

    @Test
    public void getUpcoming_nothing() {
        final List<ScreeningDto> result = underTest.getUpcoming(new Timestamp(System.currentTimeMillis() + 86400000));
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(0, result.size(), "Expected different size");
    }

    @Test
    public void getUpcoming_filtered() {
        final List<ScreeningDto> result = underTest.getUpcoming(new Timestamp(System.currentTimeMillis() + 7200000));
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(6, result.size(), "Expected different size");
    }
}

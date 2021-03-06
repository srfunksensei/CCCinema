package com.mb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.dto.*;
import com.mb.exception.ResourceNotFoundException;
import com.mb.service.IScreeningService;
import com.mb.service.ISeatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public abstract class CinemaControllerUnitTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper jsonMapper;
    
    protected abstract String getRootPath();
    protected abstract IScreeningService getScreeningService();
    protected abstract ISeatService getSeatService();

    @Test
    public void getUpcomingMovies_noScreenings() throws Exception {
        Mockito.when(getScreeningService().getUpcoming(any(Timestamp.class))).thenReturn(new ArrayList<>());

        final MvcResult mvcResult = mockMvc.perform(
                get(getRootPath() + "/upcoming"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(getScreeningService(), times(1)).getUpcoming(any(Timestamp.class));
        final List<ScreeningDto> result = jsonMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        Assertions.assertTrue(result.isEmpty(), "Expected empty result");
    }

    @Test
    public void getUpcomingMovies() throws Exception {
        final ScreeningDto dto = ScreeningDto.builder()
                .title("title")
                .description("description")
                .auditorium("auditorium")
                .director("director")
                .duration(120)
                .startingTime(new Timestamp(System.currentTimeMillis()))
                .screeningId("screening-id")
                .build();
        final List<ScreeningDto> toReturn = Stream.of(dto).collect(Collectors.toList());
        Mockito.when(getScreeningService().getUpcoming(any(Timestamp.class))).thenReturn(toReturn);

        final MvcResult mvcResult = mockMvc.perform(
                get(getRootPath() + "/upcoming"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(getScreeningService(), times(1)).getUpcoming(any(Timestamp.class));
        final List<ScreeningDto> result = jsonMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });

        Assertions.assertFalse(result.isEmpty(), "Expected empty result");
        Assertions.assertEquals(toReturn.size(), result.size(), "Expected different result");

        final ScreeningDto screeningDtoResult = toReturn.get(0);
        Assertions.assertEquals(dto.getAuditorium(), screeningDtoResult.getAuditorium(), "Expected different auditorium");
        Assertions.assertEquals(dto.getDescription(), screeningDtoResult.getDescription(), "Expected different description");
        Assertions.assertEquals(dto.getDirector(), screeningDtoResult.getDirector(), "Expected different director");
        Assertions.assertEquals(dto.getDuration(), screeningDtoResult.getDuration(), "Expected different duration");
        Assertions.assertEquals(dto.getScreeningId(), screeningDtoResult.getScreeningId(), "Expected different screening id");
        Assertions.assertEquals(dto.getStartingTime(), screeningDtoResult.getStartingTime(), "Expected different start time");
        Assertions.assertEquals(dto.getTitle(), screeningDtoResult.getTitle(), "Expected different title");
    }

    @Test
    public void getSeatsForScreening_nonExistingScreening() throws Exception {
        final String screeningId = "non-existing-screeningId";
        Mockito.doThrow(new ResourceNotFoundException()).when(getSeatService()).getSeats(eq(screeningId));

        mockMvc.perform(
                get(getRootPath() + "/{screening_id}/seats", screeningId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        Mockito.verify(getSeatService(), times(1)).getSeats(eq(screeningId));
    }


    @Test
    public void getSeatsForScreening() throws Exception {
        final String screeningId = "screeningId";

        final SeatDto seat = SeatDto.builder()
                .reserved(false)
                .row("I")
                .num("1")
                .auditorium("auditorium")
                .build();
        final Set<SeatDto> seats = Stream.of(seat).collect(Collectors.toSet());

        final ScreeningSeatsDto toReturn = ScreeningSeatsDto.builder()
                .screeningId(screeningId)
                .seats(seats)
                .build();
        Mockito.when(getSeatService().getSeats(eq(screeningId))).thenReturn(toReturn);

        final MvcResult mvcResult = mockMvc.perform(
                get(getRootPath() + "/{screening_id}/seats", screeningId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(getSeatService(), times(1)).getSeats(eq(screeningId));
        final ScreeningSeatsDto result = jsonMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screeningId, result.getScreeningId(), "Expected different screening id");
        Assertions.assertFalse(result.getSeats().isEmpty(), "Expected some seats to be returned");
        Assertions.assertEquals(seats.size(), result.getSeats().size(), "Expected same number of seats");

        final SeatDto seatResult = result.getSeats().iterator().next();
        Assertions.assertEquals(seat.getAuditorium(), seatResult.getAuditorium(), "Expected different auditorium");
        Assertions.assertEquals(seat.getNum(), seatResult.getNum(), "Expected different seat number");
        Assertions.assertEquals(seat.getRow(), seatResult.getRow(), "Expected different seat row");
        Assertions.assertEquals(seat.isReserved(), seatResult.isReserved(), "Expected different reservation value");
    }

    @Test
    public void bookSeat() throws Exception {
        final String screeningId = "screeningId";

        final ReserveDto dto = ReserveDto.builder()
                .row("I")
                .num("1")
                .username("test")
                .build();

        final SeatReservationResultDto toReturn = SeatReservationResultDto.builder()
                .screeningId(screeningId)
                .success(true)
                .message("message")
                .build();
        Mockito.when(getSeatService().bookSeat(eq(screeningId), eq(dto))).thenReturn(toReturn);

        final MvcResult mvcResult = mockMvc.perform(
                post(getRootPath() + "/{screening_id}/seats", screeningId)
                        .content(jsonMapper.writeValueAsBytes(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(getSeatService(), times(1)).bookSeat(eq(screeningId), eq(dto));
        final SeatReservationResultDto result = jsonMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
        });
        Assertions.assertNotNull(result, "Expected result");
        Assertions.assertEquals(screeningId, result.getScreeningId(), "Expected different screening id");
        Assertions.assertEquals(toReturn.getMessage(), result.getMessage(), "Expected different message");
        Assertions.assertEquals(toReturn.isSuccess(), result.isSuccess(), "Expected different success value");
    }
}

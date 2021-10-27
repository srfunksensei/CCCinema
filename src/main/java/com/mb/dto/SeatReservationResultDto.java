package com.mb.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SeatReservationResultDto {
    private String screeningId;
    private boolean success;
    private String message;
}

package com.mb.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ReserveDto {
    @NotBlank
    private String seat;
    @NotBlank
    private String username;
}

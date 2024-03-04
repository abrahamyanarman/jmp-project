package com.backend.services.jmp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionResponseDto {
    private Long id;
    private Long userId;
    private String startDate;
}

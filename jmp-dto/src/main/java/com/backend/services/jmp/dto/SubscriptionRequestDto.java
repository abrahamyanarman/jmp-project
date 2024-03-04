package com.backend.services.jmp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionRequestDto {
    private Long id;
    private Long userId;
    private String startDate;
}

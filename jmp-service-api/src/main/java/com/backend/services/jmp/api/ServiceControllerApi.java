package com.backend.services.jmp.api;

import com.backend.services.jmp.dto.SubscriptionRequestDto;
import com.backend.services.jmp.dto.SubscriptionResponseDto;

import java.util.List;

public interface ServiceControllerApi {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscription();
}

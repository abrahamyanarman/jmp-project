package com.backend.services.jmp.controller;

import com.backend.services.jmp.api.ServiceControllerApi;
import com.backend.services.jmp.dto.SubscriptionRequestDto;
import com.backend.services.jmp.dto.SubscriptionResponseDto;
import com.backend.services.jmp.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class ServiceController implements ServiceControllerApi {

    private final SubscriptionService subscriptionService;

    @Autowired
    public ServiceController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.createSubscription(subscriptionRequestDto);
    }

    @PutMapping
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionService.updateSubscription(subscriptionRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/{id}")
    public SubscriptionResponseDto getSubscription(@PathVariable Long id) {
        return subscriptionService.getSubscription(id);
    }

    @GetMapping
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionService.getAllSubscription();
    }
}

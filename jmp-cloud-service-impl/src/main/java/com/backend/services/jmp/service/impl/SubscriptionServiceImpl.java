package com.backend.services.jmp.service.impl;

import com.backend.services.jmp.dto.SubscriptionRequestDto;
import com.backend.services.jmp.dto.SubscriptionResponseDto;
import com.backend.services.jmp.dto.UserResponseDto;
import com.backend.services.jmp.entity.Subscription;
import com.backend.services.jmp.entity.User;
import com.backend.services.jmp.service.SubscriptionService;
import com.backend.services.jmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final List<Subscription> subscriptions = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final UserService userService;

    @Autowired
    public SubscriptionServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        UserResponseDto userResponseDto = userService.getUser(subscriptionRequestDto.getUserId());
        User user = new User(
                userResponseDto.getId(),
                userResponseDto.getName(),
                userResponseDto.getSurname(),
                LocalDate.parse(userResponseDto.getBirthday())
        );
        Subscription subscription = new Subscription((long) (subscriptions.size() + 1), user, LocalDate.parse(subscriptionRequestDto.getStartDate(), formatter));
        subscriptions.add(subscription);
        return new SubscriptionResponseDto(subscription.getId(), subscription.getUser().getId(), subscription.getStartDate().toString());
    }

    @Override
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        if (subscriptionRequestDto.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null for update operation");
        }

        Subscription existingSubscription = subscriptions.stream()
                .filter(subscription -> subscription.getId().equals(subscriptionRequestDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        UserResponseDto userResponseDto = userService.getUser(subscriptionRequestDto.getUserId());
        User user = new User(
                userResponseDto.getId(),
                userResponseDto.getName(),
                userResponseDto.getSurname(),
                LocalDate.parse(userResponseDto.getBirthday())
        );
        existingSubscription.setUser(user);
        existingSubscription.setStartDate(LocalDate.parse(subscriptionRequestDto.getStartDate(), formatter));

        return new SubscriptionResponseDto(existingSubscription.getId(), existingSubscription.getUser().getId(), existingSubscription.getStartDate().toString());
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptions.removeIf(subscription -> subscription.getId().equals(id));
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getId().equals(id))
                .findFirst()
                .map(subscription -> new SubscriptionResponseDto(subscription.getId(), subscription.getUser().getId(), subscription.getStartDate().toString()))
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptions.stream()
                .map(subscription -> new SubscriptionResponseDto(subscription.getId(), subscription.getUser().getId(), subscription.getStartDate().toString()))
                .collect(Collectors.toList());
    }
}

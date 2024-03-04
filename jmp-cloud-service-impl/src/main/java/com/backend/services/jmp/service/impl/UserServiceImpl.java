package com.backend.services.jmp.service.impl;

import com.backend.services.jmp.dto.UserRequestDto;
import com.backend.services.jmp.dto.UserResponseDto;
import com.backend.services.jmp.entity.User;
import com.backend.services.jmp.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = new User((long) (users.size() + 1), userRequestDto.getName(), userRequestDto.getSurname(), LocalDate.parse(userRequestDto.getBirthday(), formatter));
        users.add(user);
        return new UserResponseDto(user.getId(), user.getName(), user.getSurname(), user.getBirthday().toString());
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        if (userRequestDto.getId() == null) {
            throw new IllegalArgumentException("ID cannot be null for update operation");
        }
        User existingUser = users.stream().filter(u -> u.getId().equals(userRequestDto.getId())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userRequestDto.getName());
        existingUser.setSurname(userRequestDto.getSurname());
        existingUser.setBirthday(LocalDate.parse(userRequestDto.getBirthday(), formatter));
        return new UserResponseDto(existingUser.getId(), existingUser.getName(), existingUser.getSurname(), existingUser.getBirthday().toString());
    }

    @Override
    public void deleteUser(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getName(), user.getSurname(), user.getBirthday().toString());
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return users.stream().map(user -> new UserResponseDto(user.getId(), user.getName(), user.getSurname(), user.getBirthday().toString())).collect(Collectors.toList());
    }
}

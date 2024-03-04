package com.backend.services.jmp.service;

import com.backend.services.jmp.dto.UserRequestDto;
import com.backend.services.jmp.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserRequestDto userRequestDto);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUser();
}

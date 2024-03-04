package com.backend.services.jmp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String birthday;
}

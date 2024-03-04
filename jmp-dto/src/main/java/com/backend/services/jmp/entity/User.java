package com.backend.services.jmp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
}

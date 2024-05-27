package com.bcs.todolist.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveOrUpdatePersonDto(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotNull
        Integer roleId
) {
}

package com.bcs.todolist.role.dto;

import jakarta.validation.constraints.Pattern;

public record SaveOrUpdateRoleDto(
        @Pattern(regexp = "[a-zA-Z]+")
        String name
) {
}

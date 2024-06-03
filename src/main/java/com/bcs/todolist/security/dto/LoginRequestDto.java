package com.bcs.todolist.security.dto;

public record LoginRequestDto(
        String username,
        String password
) {
}

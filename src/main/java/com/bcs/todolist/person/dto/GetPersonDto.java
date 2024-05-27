package com.bcs.todolist.person.dto;

public record GetPersonDto(
        Integer id,
        String firstName,
        String lastName,
        Integer roleId
) {
}

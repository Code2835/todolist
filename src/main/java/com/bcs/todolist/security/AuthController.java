package com.bcs.todolist.security;

import com.bcs.todolist.person.Person;
import com.bcs.todolist.person.PersonService;
import com.bcs.todolist.security.dto.LoginRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AuthController {
    private PersonService personService;
    private JwtService jwtService;

    @Autowired
    public AuthController(PersonService personService, JwtService jwtService) {
        this.personService = personService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<Person> person = personService.getPersonByUsernameAndPassword(loginRequestDto.username(), loginRequestDto.password());

        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password is incorrect");
        }

        return jwtService.createToken(person.get());
    }
}

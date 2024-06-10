package com.bcs.todolist.security;

import com.bcs.todolist.person.Person;
import com.bcs.todolist.person.PersonService;
import com.bcs.todolist.security.dto.LoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {
    private PersonService personService;
    private JwtService jwtService;

    @Autowired
    public LoginController(PersonService personService, JwtService jwtService) {
        this.personService = personService;
        this.jwtService = jwtService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(path = "/todolist-login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public String todolistLogin(LoginRequestDto loginRequestDto, Model model) {
        Optional<Person> person = personService.getPersonByUsernameAndPassword(loginRequestDto.username(), loginRequestDto.password());

        if (person.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password is incorrect");
            model.addAttribute("error", "Username or password is incorrect");
            return "login";
        }

//        return jwtService.createToken(person.get());
        return "redirect:/greeting";
    }
}

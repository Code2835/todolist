package com.bcs.todolist.person;

import com.bcs.todolist.person.dto.GetPersonDto;
import com.bcs.todolist.person.dto.SaveOrUpdatePersonDto;
import com.bcs.todolist.role.dto.SaveOrUpdateRoleDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<GetPersonDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public GetPersonDto getPersonById(@PathVariable("id") Integer id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public void savePerson(@Valid @RequestBody SaveOrUpdatePersonDto person) {
        personService.savePerson(person);
    }


    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") Integer id, @RequestBody SaveOrUpdatePersonDto person) {
        personService.updatePerson(id, person);
    }


    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
    }

}

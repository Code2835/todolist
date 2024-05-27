package com.bcs.todolist.person;

import com.bcs.todolist.common.FileProcessor;
import com.bcs.todolist.person.dto.GetPersonDto;
import com.bcs.todolist.person.dto.SaveOrUpdatePersonDto;
import com.bcs.todolist.role.Role;
import com.bcs.todolist.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final static String DATA_FILE_NAME = "person.json";
    private FileProcessor fileProcessor;

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
//        this.fileProcessor = fileProcessor;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<GetPersonDto> getAllPersons() {
//        return fileProcessor.readAsList(PersonService.DATA_FILE_NAME, Person[].class);
//        return personRepository.findAll();
        List<Person> persons = personRepository.findAll();

        /*
        mapPersons(person) {

        }
         */

        return persons.stream()
                .map(person -> new GetPersonDto(
                        person.getId(),
                        person.getFirstName(),
                        person.getLastName(),
                        person.getRole().getId())
                )
                .toList();
    }

    public GetPersonDto getPersonById(Integer id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            Person personById = person.get();

            return new GetPersonDto(
                    personById.getId(),
                    personById.getFirstName(),
                    personById.getLastName(),
                    personById.getRole().getId()
                );
        }

//        List<Person> persons = getAllPersons();
//
//        for (Person person : persons) {
//            if (person.getId().equals(id)) {
//                return person;
//            }
//        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void savePerson(SaveOrUpdatePersonDto dto) {
        Person person = new Person();
        person.setFirstName(dto.firstName());
        person.setLastName(dto.lastName());

//        Role role = roleRepository.findById(dto.roleId());

        try {
            Role role = roleRepository.getReferenceById(dto.roleId());
            person.setRole(role);

            personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

//        List<Person> persons = getAllPersons();
//
//        persons.add(person);
//
//        fileProcessor.update(PersonService.DATA_FILE_NAME, persons);

    }

    public void updatePerson(Integer id, SaveOrUpdatePersonDto dto) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }

        String firstName = (dto.firstName() == null || dto.firstName().isEmpty()) ? person.get().getFirstName() : dto.firstName();
        String lastName = (dto.lastName() == null || dto.lastName().isEmpty()) ? person.get().getLastName() : dto.lastName();
        Integer roleId = dto.roleId() == null ? person.get().getRole().getId() : dto.roleId();

        Optional<Role> role = roleRepository.findById(roleId);

        if (role.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }

        try {
            personRepository.update(id, firstName, lastName, role.get());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        personRepository.update(id, firstName, lastName, role.get());
    }


    public void deletePerson(Integer id) {
        personRepository.deleteById(id);

//        List<Person> persons = getAllPersons();
//
//        for (Person person : persons) {
//            if (person.getId().equals(id)) {
//                persons.remove(person);
//                break;
//            }
//        }
//
//        fileProcessor.update(PersonService.DATA_FILE_NAME, persons);
    }


}

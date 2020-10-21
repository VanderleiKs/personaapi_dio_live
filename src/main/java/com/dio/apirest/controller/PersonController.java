package com.dio.apirest.controller;

import com.dio.apirest.dto.MessageResponseDTO;
import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.entity.Person;
import com.dio.apirest.service.PersonService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        return personService.getAllPerson();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO savePerson(@RequestBody @Valid PersonDTO personDTO){
       return personService.savePerson(personDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person person){
        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        return personService.deletePerson(id);
    }

}





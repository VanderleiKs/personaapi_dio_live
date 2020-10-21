package com.dio.apirest.controller;

import com.dio.apirest.dto.response.MessageResponseDTO;
import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.service.PersonService;
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
    public ResponseEntity<List<PersonDTO>> getAllPersons(){
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id){
        return personService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponseDTO> savePerson(@RequestBody @Valid PersonDTO personDTO){
       return personService.savePerson(personDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> updatePerson(@PathVariable @Valid Long id, @RequestBody PersonDTO person){
        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deletePerson(@PathVariable Long id){
        return personService.deletePerson(id);
    }

}
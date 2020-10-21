package com.dio.apirest.controller;

import com.dio.apirest.entity.Person;
import com.dio.apirest.service.PersonService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        return personService.getAllPerson();
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person){
        return personService.savePerson(person);
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





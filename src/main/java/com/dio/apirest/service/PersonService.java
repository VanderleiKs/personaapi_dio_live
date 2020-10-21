package com.dio.apirest.service;

import com.dio.apirest.entity.Person;
import com.dio.apirest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    //GetAll
    public ResponseEntity<List<Person>> getAllPerson(){
        return ResponseEntity.ok().body(personRepository.findAll());
    }

    //Save
    public ResponseEntity<Person> savePerson(Person person){
        personRepository.save(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.ok("Salvo Com Sucesso!").created(uri).build();
    }

    //Update
    public ResponseEntity<String> updatePerson(Person person, Long id){
        Optional<Person> personSave = personRepository.findById(id);
        personSave.get().setFirstName(person.getFirstName());
        personSave.get().setLastName(person.getLastName());
        personRepository.save(personSave.get());
        return ResponseEntity.ok("Atualizado com Sucesso!");
    }

    //Delete
    public ResponseEntity<String> deletePerson(Long id){
        personRepository.deleteById(id);
        return ResponseEntity.ok("Excluded com Success!");
    }
}

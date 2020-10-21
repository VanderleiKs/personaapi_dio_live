package com.dio.apirest.service;

import com.dio.apirest.dto.MessageResponseDTO;
import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.entity.Person;
import com.dio.apirest.exception.PersonNotFoundException;
import com.dio.apirest.mapper.PersonMapper;
import com.dio.apirest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    final
    PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //GetAll
    public ResponseEntity<List<PersonDTO>> getAllPerson(){
        return ResponseEntity.ok().body(personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList()));
    }

    //GetById
    public ResponseEntity<PersonDTO> getById(Long id){
        Person person = verifyIfExist(id);
        return ResponseEntity.ok(personMapper.toDTO(person));
    }

    //Save
    public MessageResponseDTO savePerson(PersonDTO personDTO){
        Person p = personMapper.toModel(personDTO);
        Person personSave = personRepository.save(p);
        return MessageResponseDTO
                .builder()
                .message("Save with success, id: " + personSave.getId())
                .build();
    }

    //Update
    public ResponseEntity<String> updatePerson(Person person, Long id){
        Person personSave = verifyIfExist(id);
        personSave.setFirstName(person.getFirstName());
        personSave.setLastName(person.getLastName());
        personRepository.save(personSave);
        return ResponseEntity.ok("Update with success!");
    }

    //Delete
    public ResponseEntity<MessageResponseDTO> deletePerson(Long id){
        personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        personRepository.deleteById(id);
        return ResponseEntity.ok(MessageResponseDTO
                .builder()
                .message("Excluded com Success!")
                .build());
    }

    //find id and verify if exist
    private Person verifyIfExist(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }
}

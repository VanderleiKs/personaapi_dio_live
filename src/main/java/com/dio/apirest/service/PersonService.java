package com.dio.apirest.service;

import com.dio.apirest.dto.response.MessageResponseDTO;
import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.entity.Person;
import com.dio.apirest.exception.PersonNotFoundException;
import com.dio.apirest.mapper.PersonMapper;
import com.dio.apirest.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

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
    public ResponseEntity<MessageResponseDTO> savePerson(PersonDTO personDTO){
            Person personToSave = personMapper.toModel(personDTO);
            Person testCpf = personRepository.findByCPF(personToSave.getCpf());
            if(testCpf != null){
                return responseMessage("CPF already registered");
            }
            Person personSave = personRepository.save(personToSave);
            return responseMessage("Save with success, id: " + personSave.getId());
    }

    //Update
    public ResponseEntity<MessageResponseDTO> updatePerson(PersonDTO person, Long id){
        Person personSave = verifyIfExist(id);
        personSave.setFirstName(person.getFirstName());
        personSave.setLastName(person.getLastName());
        personRepository.save(personSave);
        return responseMessage("Update with success!");
    }

    //Delete
    public ResponseEntity<MessageResponseDTO> deletePerson(Long id){
        verifyIfExist(id);
        personRepository.deleteById(id);
        return responseMessage("Excluded com Success!");
    }

    //find id and verify if exist
    private Person verifyIfExist(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
    }

    //Response Method
    private ResponseEntity<MessageResponseDTO> responseMessage(String s) {
        return ResponseEntity.ok(MessageResponseDTO
                .builder()
                .message(s)
                .build());
    }
}

package com.dio.apirest.controller;

import com.dio.apirest.dto.request.PersonDTO;
import com.dio.apirest.dto.response.MessageResponseDTO;
import com.dio.apirest.exception.PersonNotFoundException;
import com.dio.apirest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons(){
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable Long id){
            return personService.getById(id);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public String erro(){
//        return "Erro na sua requisicao";
//    }

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
    public ResponseEntity<MessageResponseDTO> deletePerson(@PathVariable Long id)  {
        return personService.deletePerson(id);
    }

}
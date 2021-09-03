package com.erickmarques.personapi.controllers;

import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person createdPerson = personRepository.save(person);

        return MessageResponseDTO.builder()
                .message("Created person with ID"+createdPerson.getId()).build();
    }

}

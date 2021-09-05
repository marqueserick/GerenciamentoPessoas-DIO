package com.erickmarques.personapi.controllers;

import com.erickmarques.personapi.dto.request.PersonDTO;
import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.create(personDTO);
    }

    @GetMapping(value = "/all")
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

}

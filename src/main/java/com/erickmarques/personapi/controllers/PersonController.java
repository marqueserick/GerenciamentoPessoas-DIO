package com.erickmarques.personapi.controllers;

import com.erickmarques.personapi.dto.request.PersonDTO;
import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.exceptions.PersonNotFoundException;
import com.erickmarques.personapi.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    private PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping(value = "/all")
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping(value="/{id}")
    public MessageResponseDTO updatePerson(
            @PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException{
       return personService.updatePerson(id, personDTO);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) throws PersonNotFoundException{
        personService.deletePerson(id);
    }

}

package com.erickmarques.personapi.services;

import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO create(Person person){
        Person createdPerson = personRepository.save(person);
        return MessageResponseDTO.builder()
                .message("Created person with ID"+createdPerson.getId()).build();
    }
}

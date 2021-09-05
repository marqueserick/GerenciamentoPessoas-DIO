package com.erickmarques.personapi.services;

import com.erickmarques.personapi.dto.mappers.PersonMapper;
import com.erickmarques.personapi.dto.request.PersonDTO;
import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public MessageResponseDTO create(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO.builder()
                .message("Created person with ID"+savedPerson.getId()).build();
    }

    public List<PersonDTO> listAll(){
        List<Person> people = personRepository.findAll();
        return people.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }
}

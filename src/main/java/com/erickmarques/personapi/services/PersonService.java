package com.erickmarques.personapi.services;

import com.erickmarques.personapi.dto.mappers.PersonMapper;
import com.erickmarques.personapi.dto.request.PersonDTO;
import com.erickmarques.personapi.dto.response.MessageResponseDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.exceptions.PersonNotFoundException;
import com.erickmarques.personapi.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person savedPerson = savePersonDTO(personDTO);
        return createMessage(savedPerson.getId(),"created person with id ");
    }

    public List<PersonDTO> listAll(){
        List<Person> people = personRepository.findAll();
        return people.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        return personMapper.toDTO(verifyIfExists(id));
    }

    public void deletePerson(Long id) throws PersonNotFoundException{
        personRepository.delete(verifyIfExists(id));
    }
    public MessageResponseDTO updatePerson(Long id, PersonDTO personDTO)throws PersonNotFoundException{
        verifyIfExists(id);
        Person updatedPerson = savePersonDTO(personDTO);
        return createMessage(updatedPerson.getId(),"updated person with id ");
    }

    private Person savePersonDTO(PersonDTO personDTO){
        return personRepository.save(personMapper.toModel(personDTO));
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessage(Long id, String message){
        return MessageResponseDTO.builder()
                .message(message+id).build();
    }
}

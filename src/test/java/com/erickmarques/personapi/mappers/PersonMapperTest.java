package com.erickmarques.personapi.mappers;

import com.erickmarques.personapi.dto.mappers.PersonMapper;
import com.erickmarques.personapi.dto.request.PersonDTO;
import com.erickmarques.personapi.dto.request.PhoneDTO;
import com.erickmarques.personapi.entities.Person;
import com.erickmarques.personapi.entities.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.erickmarques.personapi.utils.PersonUtils.createFakeDTO;
import static com.erickmarques.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    void testGivenPersonDTOThenReturnPersonEntity() {
        PersonDTO personDTO = createFakeDTO();
        Person person = personMapper.toModel(personDTO);

        assertEquals(personDTO.getFirstName(), person.getFirstName());
        assertEquals(personDTO.getLastName(), person.getLastName());
        assertEquals(personDTO.getCpf(), person.getCpf());

        Phone phone = person.getPhones().get(0);
        PhoneDTO phoneDTO = personDTO.getPhones().get(0);

        assertEquals(phoneDTO.getType(), phone.getType());
        assertEquals(phoneDTO.getPhoneNumber(), phone.getPhoneNumber());
    }

    @Test
    void testGivenPersonEntityThenReturnPersonDTO() {
        Person person = createFakeEntity();
        PersonDTO personDTO = personMapper.toDTO(person);

        assertEquals(person.getFirstName(), personDTO.getFirstName());
        assertEquals(person.getLastName(), personDTO.getLastName());
        assertEquals(person.getCpf(), personDTO.getCpf());

        Phone phone = person.getPhones().get(0);
        PhoneDTO phoneDTO = personDTO.getPhones().get(0);

        assertEquals(phone.getType(), phoneDTO.getType());
        assertEquals(phone.getPhoneNumber(), phoneDTO.getPhoneNumber());
    }
}

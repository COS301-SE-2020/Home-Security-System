package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.PersonDTO;
import com.springboot.SpringBackend.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDTO implements Converter<Person, PersonDTO> {
    @Override
    public PersonDTO convert(Person x) {
        PersonDTO dto = new PersonDTO();
        dto.setPersonId(x.getPersonId());
        dto.setFullName(x.getFullName());
        dto.setListed(x.getListed());
        dto.setDate(x.getDate());
        dto.setImage(x.getImage());
        return dto;
    }
}

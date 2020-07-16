package com.springboot.SpringBackend.converter;

import com.springboot.SpringBackend.dto.PersonDTO;
import com.springboot.SpringBackend.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PersonDTOToPerson implements Converter<PersonDTO, Person> {
    @Override
    public Person convert(PersonDTO dto) {
        Person x = new Person();
        if (dto.getPersonId() != null && !StringUtils.isEmpty(dto.getPersonId())) {
            x.setPersonId(dto.getPersonId());
        }
        x.setFullName(dto.getFullName());
        x.setListed(dto.getListed());
        x.setDate(dto.getDate());
        x.setImage(dto.getImage());
        return x;
    }
}

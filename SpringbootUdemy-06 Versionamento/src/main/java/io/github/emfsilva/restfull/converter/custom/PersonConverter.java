package io.github.emfsilva.restfull.converter.custom;

import io.github.emfsilva.restfull.data.dto.v2.PersonDTOV2;
import io.github.emfsilva.restfull.data.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonConverter {

    public PersonDTOV2 convertEntityToDTO(Person entity) {
        PersonDTOV2 dto = new PersonDTOV2();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setBirthDay(new Date());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 dto) {
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        return entity;
    }

}

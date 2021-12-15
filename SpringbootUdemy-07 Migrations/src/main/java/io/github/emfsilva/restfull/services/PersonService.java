package io.github.emfsilva.restfull.services;

import io.github.emfsilva.restfull.converter.DozerConverter;
import io.github.emfsilva.restfull.data.dto.PersonDTO;
import io.github.emfsilva.restfull.data.model.Person;
import io.github.emfsilva.restfull.exception.ResourceNotFoundException;
import io.github.emfsilva.restfull.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDTO create(PersonDTO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var dto = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public List<PersonDTO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}

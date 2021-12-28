package io.github.emfsilva.services;

import io.github.emfsilva.converter.DozerConverter;
import io.github.emfsilva.data.model.Person;
import io.github.emfsilva.data.vo.v1.PersonDTO;
import io.github.emfsilva.exception.ResourceNotFoundException;
import io.github.emfsilva.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonDTO create(PersonDTO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
        return vo;
    }


    public Page<PersonDTO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::converterToPersonDTO);
    }

    public Page<PersonDTO> findPersonByName(String firstName, Pageable pageable) {
        var page = repository.findPersonByName(firstName,pageable);
        return page.map(this::converterToPersonDTO);
    }

    private PersonDTO converterToPersonDTO(Person person) {
        return DozerConverter.parseObject(person, PersonDTO.class);
    }

    public PersonDTO findById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
        return vo;
    }

    @Transactional
    public PersonDTO disablePersons(Long id) {
        repository.disablePersons(id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonDTO.class);
    }

    @Transactional
    public PersonDTO enablePersons(Long id) {
        repository.enablePersons(id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonDTO.class);
    }


    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}

package io.github.emfsilva.restfull.services;

import io.github.emfsilva.restfull.converter.DozerConverter;
import io.github.emfsilva.restfull.data.vo.PersonVO;
import io.github.emfsilva.restfull.exception.ResourceNotFoundException;
import io.github.emfsilva.restfull.data.model.Person;
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

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity,PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}

package io.github.emfsilva.restfull.services;

import io.github.emfsilva.restfull.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public Person findById(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Emerson");
        person.setLastName("Ferreira");
        person.setAddress("Sao Paulo - Brazil");
        person.setGender("Male");
        return person;
    }
}

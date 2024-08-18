package com.example.readreplicapoc.service;

import com.example.readreplicapoc.entity.Person;
import com.example.readreplicapoc.models.PersonResponse;
import com.example.readreplicapoc.models.SavePersonRequest;
import com.example.readreplicapoc.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonResponse save(final SavePersonRequest savePersonRequest){
        final var person = new Person();
        person.setAge(savePersonRequest.age());
        person.setFirstName(savePersonRequest.firstName());
        person.setLastName(savePersonRequest.lastName());
        person.setMiddleName(savePersonRequest.middleName());
        final var savePerson = personRepository.save(person);
        return new PersonResponse(
                savePerson.getId(),
                savePerson.getFirstName(),
                savePerson.getLastName(),
                savePerson.getMiddleName(),
                savePerson.getAge()
        );
    }

    @Transactional(readOnly = true)
    public List<PersonResponse> findAll(){
        final var persons = personRepository.findAll();
        final var personResponseList = new ArrayList<PersonResponse>();
        for(var person : persons){
            personResponseList.add(
                    new PersonResponse(
                            person.getId(),
                            person.getFirstName(),
                            person.getLastName(),
                            person.getMiddleName(),
                            person.getAge()
                    )
            );
        }
        return personResponseList;
    }
}
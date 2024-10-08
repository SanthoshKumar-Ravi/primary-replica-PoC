package com.poc.readreplica.controller;

import com.poc.readreplica.api.PersonApi;
import com.poc.readreplica.api.model.Person;
import com.poc.readreplica.api.model.PersonInput;
import com.poc.readreplica.mapper.PersonMapper;
import com.poc.readreplica.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController implements PersonApi {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @Override
    public ResponseEntity<Person> personPost(PersonInput personInput) {
       final var serviceRequest = PersonMapper.toSaveServiceRequest(personInput);
       final var serviceResponse = personService.save(serviceRequest);
       return ResponseEntity.ok(PersonMapper.toSaveApiResponse(serviceResponse));
    }

    @Override
    public ResponseEntity<List<Person>> personGet() {
        final var serviceResponse = personService.findAll();
        return ResponseEntity.ok(PersonMapper.toFindAllApiResponse(serviceResponse));
    }
}

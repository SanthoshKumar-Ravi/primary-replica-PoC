package com.poc.readreplica.mapper;

import com.poc.readreplica.api.model.Person;
import com.poc.readreplica.api.model.PersonInput;
import com.poc.readreplica.models.SavePersonRequest;
import com.poc.readreplica.models.PersonResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    public static SavePersonRequest toSaveServiceRequest(PersonInput personInput) {
        return new SavePersonRequest(
                personInput.getFirstName(),
                personInput.getLastName(),
                personInput.getMiddleName(),
                personInput.getAge()
        );
    }

    public static Person toSaveApiResponse(PersonResponse serviceResponse) {
        final var person = new Person();
        person.setId(serviceResponse.id());
        person.setFirstName(serviceResponse.firstName());
        person.setLastName(serviceResponse.lastName());
        person.setMiddleName(serviceResponse.middleName());
        person.setAge(serviceResponse.age());
        return person;
    }

    public static List<Person> toFindAllApiResponse(List<PersonResponse> serviceResponse) {
        return serviceResponse.stream().map(PersonMapper::toSaveApiResponse).collect(Collectors.toList());
    }
}

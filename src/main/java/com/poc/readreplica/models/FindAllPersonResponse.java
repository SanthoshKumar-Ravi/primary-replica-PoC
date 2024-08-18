package com.poc.readreplica.models;

import java.util.List;

public record FindAllPersonResponse(
    List<PersonResponse> personResponseList
) {
}

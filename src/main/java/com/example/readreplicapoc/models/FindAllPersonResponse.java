package com.example.readreplicapoc.models;

import java.util.List;

public record FindAllPersonResponse(
    List<PersonResponse> personResponseList
) {
}

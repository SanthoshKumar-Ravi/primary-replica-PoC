package com.poc.readreplica.models;

import javax.annotation.Nonnull;

public record PersonResponse(
        @Nonnull
        Integer id,
        @Nonnull
        String firstName,
        @Nonnull
        String lastName,
        @Nonnull
        String middleName,
        @Nonnull
        int age
) {
}

package com.poc.readreplica.models;

import javax.annotation.Nonnull;

public record SavePersonRequest(
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

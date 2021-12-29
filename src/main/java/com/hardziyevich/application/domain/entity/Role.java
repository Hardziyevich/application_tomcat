package com.hardziyevich.application.domain.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER,
    ADMIN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static Role findRole(String role) {
        return Arrays.stream(values())
                .filter(r -> r.toString().equals(role))
                .findFirst().orElse(USER);
    }
}

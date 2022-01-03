package com.hardziyevich.application.domain.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Validator<T> {

    private final T object;
    private final List<String> container = new ArrayList<>();

    private Validator(T object) {
        this.object = object;
    }

    public static <T> Validator<T> of(T object) {
        return new Validator<>(object);
    }

    public Validator<T> validator(Predicate<T> predicate, String key) {
        if(!predicate.test(object)) {
            container.add(key);
        }
        return this;
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public List<String> getContainer() {
        return container;
    }
}

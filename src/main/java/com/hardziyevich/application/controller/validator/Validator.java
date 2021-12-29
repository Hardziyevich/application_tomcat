package com.hardziyevich.application.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Validator<T> {

    private final T object;
    private final Map<String,String> container = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    private Validator(T object) {
        this.object = object;
    }

    public static <T> Validator<T> of(T object) {
        return new Validator<>(object);
    }

    public Validator<T> validator(Predicate<T> predicate, String key, String message) {
        if(!predicate.test(object)) {
            log.info(message);
            container.put(key,message);
        }
        return this;
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    public Map<String, String> getContainer() {
        return container;
    }
}

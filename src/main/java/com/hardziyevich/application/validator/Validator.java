package com.hardziyevich.application.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Validator<T> {

    private final T object;
    private final Map<String,String> container = new HashMap<>();

    private Validator(T object) {
        this.object = object;
    }

    public static <T> Validator<T> of(T object) {
        return new Validator<>(object);
    }

    public Validator<T> validator(Predicate<T> predicate, String key, String message) {
        if(!predicate.test(object)) {
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

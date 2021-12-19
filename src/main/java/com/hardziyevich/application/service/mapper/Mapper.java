package com.hardziyevich.application.service.mapper;

public interface Mapper<F,T> {
    T mapFrom(F object);
}

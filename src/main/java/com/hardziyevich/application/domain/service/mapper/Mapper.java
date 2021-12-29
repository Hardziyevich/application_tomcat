package com.hardziyevich.application.domain.service.mapper;

@FunctionalInterface
public interface Mapper<F,T> {

    T mapFrom(F object);

}

package com.hardziyevich.application.domain.service.mapper;

public interface Mapper<F,T> {

    T mapFrom(F object);

}

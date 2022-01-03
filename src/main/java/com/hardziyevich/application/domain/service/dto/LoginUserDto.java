package com.hardziyevich.application.domain.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginUserDto {

    String firstName;
    String lastName;
    String email;
    String type;

}

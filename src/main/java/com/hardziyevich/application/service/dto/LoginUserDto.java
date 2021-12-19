package com.hardziyevich.application.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginUserDto {

    String firstName;
    String lastName;
    String login;
    String type;

}

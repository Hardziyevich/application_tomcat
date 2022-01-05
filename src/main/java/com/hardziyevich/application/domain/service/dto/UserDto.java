package com.hardziyevich.application.domain.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@Value
public class UserDto {

    String firstName;
    String lastName;
    String email;
    String password;
    String type;

}

package com.hardziyevich.application.validator;

import com.hardziyevich.application.service.dto.UserDto;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;

import static com.hardziyevich.application.validator.Test.ConstantValid.*;

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);
    private Map<String,String> error;

    public boolean isValid(UserDto object) {
        Predicate<UserDto> email = x -> EmailValidator.getInstance().isValid(x.getLogin());
        Validator<UserDto> validator = Validator.of(object)
                .validator(email,EMAIL_NOT_VALID,"Email is not valid");
        if(!validator.isEmpty()) {
            error = validator.getContainer();
        }
        return validator.isEmpty();

    }


    public Map<String,String> getContents() {
        return error;
    }

    static class ConstantValid {
        static final String EMAIL_NOT_VALID = "invalid.email";
    }
}

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
//
        Validator<UserDto> validator = Validator.of(object)
                .validator(u -> EmailValidator.getInstance().isValid(u.getLogin()),EMAIL_NOT_VALID,"Email is not valid")
                .validator(u -> !u.getFirstName().isEmpty(),NAME_NOT_VALID,"First name is not allow to be empty.")
                .validator(u -> !u.getFirstName().isEmpty(),LAST_NAME_NOT_VALID,"Last name is not allow to be empty.");

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
        static final String NAME_NOT_VALID = "invalid.first.name";
        static final String LAST_NAME_NOT_VALID = "invalid.last.name";
    }
}

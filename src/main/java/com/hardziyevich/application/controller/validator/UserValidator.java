package com.hardziyevich.application.controller.validator;

import com.hardziyevich.application.domain.service.dto.UserDto;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.*;

import static com.hardziyevich.application.controller.validator.UserValidator.ConstantValid.*;

public class UserValidator {

    private Map<String, String> error = new HashMap<>();

    public boolean isValid(UserDto object) {
        Validator<UserDto> validator;
        if (object != null) {
            validator = Validator.of(object)
                    .validator(u -> EmailValidator.getInstance().isValid(u.getLogin()), EMAIL_NOT_VALID, "Email is not valid")
                    .validator(u -> !u.getFirstName().isEmpty(), NAME_NOT_VALID, "First name is not allow to be empty.")
                    .validator(u -> !u.getFirstName().isEmpty(), LAST_NAME_NOT_VALID, "Last name is not allow to be empty.");
            if (!validator.isEmpty()) {
                error = validator.getContainer();
            }
        } else {
            error.put("error","Null dto");

        }
        return error.isEmpty();
    }


    public Map<String, String> getContents() {
        return error;
    }

    static class ConstantValid {
        static final String EMAIL_NOT_VALID = "invalid.email";
        static final String NAME_NOT_VALID = "invalid.first.name";
        static final String LAST_NAME_NOT_VALID = "invalid.last.name";
    }
}

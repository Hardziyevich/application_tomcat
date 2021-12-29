package com.hardziyevich.application.domain.service.dto;

public final class UserDto {

    private final String firstName;
    private final String lastName;
    private final String login;
    private final String password;
    private final String type;

    private UserDto(UserDtoBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.password = builder.password;
        this.type = builder.type;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserDto other = (UserDto) o;
        final Object thisFirstName = this.getFirstName();
        final Object otherFirstName = other.getFirstName();
        if (thisFirstName == null ? otherFirstName != null : !thisFirstName.equals(otherFirstName)) return false;
        final Object thisLastName = this.getLastName();
        final Object otherLastName = other.getLastName();
        if (thisLastName == null ? otherLastName != null : !thisLastName.equals(otherLastName)) return false;
        final Object thisLogin = this.getLogin();
        final Object otherLogin = other.getLogin();
        if (thisLogin == null ? otherLogin != null : !thisLogin.equals(otherLogin)) return false;
        final Object thisPassword = this.getPassword();
        final Object otherPassword = other.getPassword();
        if (thisPassword == null ? otherPassword != null : !thisPassword.equals(otherPassword)) return false;
        final Object thisType = this.getType();
        final Object otherType = other.getType();
        if (thisType == null ? otherType != null : !thisType.equals(otherType)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 43;
        int result = 1;
        result = result * PRIME + (firstName == null ? 0 : firstName.hashCode());
        result = result * PRIME + (lastName == null ? 0 : lastName.hashCode());
        result = result * PRIME + (login == null ? 0 : login.hashCode());
        result = result * PRIME + (password == null ? 0 : password.hashCode());
        result = result * PRIME + (type == null ? 0 : type.hashCode());
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UserDto(firstName=")
                .append(this.getFirstName())
                .append(", lastName=").append(this.getLastName())
                .append(", login=").append(this.getLogin())
                .append(", password=").append(this.getPassword())
                .append(", type=").append(this.getType()).append(")");
        return sb.toString();
    }

    public static class UserDtoBuilder {
        private String firstName;
        private String lastName;
        private String login;
        private String password;
        private String type;

        UserDtoBuilder() {
        }

        public UserDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}

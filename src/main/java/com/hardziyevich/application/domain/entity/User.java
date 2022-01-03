package com.hardziyevich.application.domain.entity;

import java.math.BigDecimal;

public final class User {
    private final BigDecimal id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Role type;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.type = builder.type;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public BigDecimal getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getType() {
        return this.type;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User other = (User) o;
        final Object thisId = this.getId();
        final Object otherId = other.getId();
        if (thisId == null ? otherId != null : !thisId.equals(otherId)) return false;
        final Object thisFirstName = this.getFirstName();
        final Object otherFirstName = other.getFirstName();
        if (thisFirstName == null ? otherFirstName != null : !thisFirstName.equals(otherFirstName)) return false;
        final Object thisLastName = this.getLastName();
        final Object otherLastName = other.getLastName();
        if (thisLastName == null ? otherLastName != null : !thisLastName.equals(otherLastName)) return false;
        final Object thisEmail = this.getEmail();
        final Object otherEmail = other.getEmail();
        if (thisEmail == null ? otherEmail != null : !thisEmail.equals(otherEmail)) return false;
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
        result = result * PRIME + (id == null ? 43 : id.hashCode());
        result = result * PRIME + (firstName == null ? 43 : firstName.hashCode());
        result = result * PRIME + (lastName == null ? 43 : lastName.hashCode());
        result = result * PRIME + (email == null ? 43 : email.hashCode());
        result = result * PRIME + (password == null ? 43 : password.hashCode());
        result = result * PRIME + (type == null ? 43 : type.hashCode());
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("User(id=")
                .append(this.getId())
                .append(", firstName=").append(this.getFirstName())
                .append(", lastName=").append(this.getLastName())
                .append(", email=").append(this.getEmail())
                .append(", password=").append(this.getPassword())
                .append(", type=").append(this.getType()).append(")");
        return sb.toString();
    }

    public static class UserBuilder {
        private BigDecimal id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role type;

        public UserBuilder id(BigDecimal id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder type(Role type) {
            this.type = type;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

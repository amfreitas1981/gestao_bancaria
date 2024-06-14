package com.banking.account.transact.domain.username;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataUserRegistration(
        @NotBlank(message = "Name is mandatory field")
        String name,
        @NotBlank(message = "E-mail is mandatory field")
        @Email(message = "E-mail in invalid format")
        String login,
        @NotBlank(message = "Password is mandatory field")
        String password,
        Boolean admin) {
}

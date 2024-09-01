package com.cainant.testebackendtgid.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountUpdateData(
        @Email
        @NotBlank
        String email) {
}

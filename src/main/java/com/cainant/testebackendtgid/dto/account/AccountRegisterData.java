package com.cainant.testebackendtgid.dto.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRegisterData(
        @NotBlank
        String name,

        @NotBlank
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotNull
        Long enterpriseId
) {
}

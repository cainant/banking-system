package com.cainant.testebackendtgid.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClientCompanyRegisterData(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @CPF
        String cpf
) {
}

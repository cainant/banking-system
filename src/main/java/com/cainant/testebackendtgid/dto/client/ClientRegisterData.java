package com.cainant.testebackendtgid.dto.client;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClientRegisterData(
        @NotBlank
        String name,

        @NotBlank
        @CPF
        String cpf
) {
}

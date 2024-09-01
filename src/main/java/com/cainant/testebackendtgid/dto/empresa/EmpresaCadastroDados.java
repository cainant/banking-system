package com.cainant.testebackendtgid.dto.empresa;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record EmpresaCadastroDados(
        @NotBlank
        String nome,

        @NotBlank
        @CNPJ
        String cnpj

) {
}

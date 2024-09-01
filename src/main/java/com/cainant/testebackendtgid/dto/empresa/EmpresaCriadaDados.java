package com.cainant.testebackendtgid.dto.empresa;

import com.cainant.testebackendtgid.domain.Empresa;

public record EmpresaCriadaDados(
        Long id,
        String nome,
        String cnpj
) {
    public EmpresaCriadaDados(Empresa empresa) {
        this(empresa.getId(), empresa.getNome(), empresa.getCnpj());
    }
}

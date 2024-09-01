package com.cainant.testebackendtgid.dto.empresa;

import com.cainant.testebackendtgid.domain.Empresa;

public record EmpresaListarDados(
        Long id,
        String nome,
        String cnpj,
        Long saldo
) {
    public EmpresaListarDados(Empresa empresa) {
        this(empresa.getId(), empresa.getNome(), empresa.getCnpj(), empresa.getSaldo());
    }
}

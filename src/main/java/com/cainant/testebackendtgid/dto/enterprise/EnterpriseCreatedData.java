package com.cainant.testebackendtgid.dto.enterprise;

import com.cainant.testebackendtgid.domain.Enterprise;

public record EnterpriseCreatedData(
        Long id,
        String name,
        String email,
        String cnpj
) {
    public EnterpriseCreatedData(Enterprise enterprise) {
        this(enterprise.getId(), enterprise.getEmail(),enterprise.getName(), enterprise.getCnpj());
    }
}

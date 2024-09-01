package com.cainant.testebackendtgid.dto.enterprise;

import com.cainant.testebackendtgid.domain.Enterprise;

public record EnterpriseCreatedData(
        Long id,
        String name,
        String cnpj
) {
    public EnterpriseCreatedData(Enterprise enterprise) {
        this(enterprise.getId(), enterprise.getName(), enterprise.getCnpj());
    }
}

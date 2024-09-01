package com.cainant.testebackendtgid.dto.enterprise;

import com.cainant.testebackendtgid.domain.Enterprise;

public record EnterpriseListData(
        Long id,
        String name,
        String cnpj,
        Long balance
) {
    public EnterpriseListData(Enterprise enterprise) {
        this(enterprise.getId(), enterprise.getName(), enterprise.getCnpj(), enterprise.getBalance());
    }
}

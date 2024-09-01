package com.cainant.testebackendtgid.dto.client;

import com.cainant.testebackendtgid.domain.Client;

// todo: listar todas as empresas que cliente esta cadastrado
public record ClientListData(
        Long id,
        String name,
        String cpf
) {
    public ClientListData(Client client) {
        this(client.getId(), client.getName(), client.getCpf());
    }
}

package com.cainant.testebackendtgid.dto.client;

import com.cainant.testebackendtgid.domain.Client;

public record ClientCreatedData(
        Long id,
        String nome,
        String cpf
) {
    public ClientCreatedData(Client client) {
        this(client.getId(), client.getName(), client.getCpf());
    }
}

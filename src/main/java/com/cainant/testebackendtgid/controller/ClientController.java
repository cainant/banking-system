package com.cainant.testebackendtgid.controller;

import com.cainant.testebackendtgid.domain.Account;
import com.cainant.testebackendtgid.domain.Client;
import com.cainant.testebackendtgid.dto.client.ClientRegisterData;
import com.cainant.testebackendtgid.dto.client.ClientCreatedData;
import com.cainant.testebackendtgid.dto.client.ClientListData;
import com.cainant.testebackendtgid.dto.client.ClientUpdateData;
import com.cainant.testebackendtgid.repository.AccountRepository;
import com.cainant.testebackendtgid.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ClientCreatedData> register(@RequestBody @Valid ClientRegisterData clientRegisterData, UriComponentsBuilder uriBuilder) {
        var client = new Client(clientRegisterData);
        clientRepository.save(client);

        var uri = uriBuilder.path("clients/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClientCreatedData(client));
    }

    @GetMapping
    public ResponseEntity<Page<ClientListData>> list(Pageable pagination) {
        var page = clientRepository.findAll(pagination)
                .map(ClientListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Account>> getAccountsById(@PathVariable Long id, Pageable pagination) {
        var client = clientRepository.getReferenceById(id);

        var accounts = accountRepository.findAccountsByClientId(pagination, client.getId());

        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateData clientUpdateData) {
        var client = clientRepository.getReferenceById(id);

        client.setName(clientUpdateData.name());

        return ResponseEntity.ok().build();
    }
}

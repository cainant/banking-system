package com.cainant.testebackendtgid.controller;

import com.cainant.testebackendtgid.domain.Account;
import com.cainant.testebackendtgid.domain.Client;
import com.cainant.testebackendtgid.dto.account.AccountCreatedData;
import com.cainant.testebackendtgid.dto.account.AccountTransactionData;
import com.cainant.testebackendtgid.dto.account.AccountRegisterData;
import com.cainant.testebackendtgid.dto.account.AccountUpdateData;
import com.cainant.testebackendtgid.repository.AccountRepository;
import com.cainant.testebackendtgid.repository.ClientRepository;
import com.cainant.testebackendtgid.repository.EnterpriseRepository;
import com.cainant.testebackendtgid.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("accounts")
public class AccountController {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Transactional
    public ResponseEntity<AccountCreatedData> newClient(@RequestBody @Valid AccountRegisterData accountData, UriComponentsBuilder uriBuilder) {
        var enterprise = enterpriseRepository.getReferenceById(accountData.enterpriseId());

        var client = clientRepository.findByCpf(accountData.cpf());
        if (client == null) {
            client = new Client(accountData);
            clientRepository.save(client);
        }

        var account = new Account(accountData, client, enterprise);
        accountRepository.save(account);

        var uri = uriBuilder.path("account/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new AccountCreatedData(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        var account = accountRepository.getReferenceById(id);

        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateEmail(@PathVariable Long id, @RequestBody @Valid AccountUpdateData accountUpdateData) {
        var account = accountRepository.getReferenceById(id);
        account.setEmail(accountUpdateData.email());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/deposit")
    @Transactional
    public ResponseEntity<Object> deposit(@PathVariable Long id, @RequestBody @Valid AccountTransactionData accountTransactionData) {
        var account = accountRepository.getReferenceById(id);
        var enterprise = account.getEnterprise();

        return transactionService.deposit(account, enterprise, accountTransactionData);
    }

    @PostMapping("/{id}/withdrawal")
    @Transactional
    public ResponseEntity<Object> withdrawal(@PathVariable Long id, @RequestBody @Valid AccountTransactionData accountTransactionData) {
        var account = accountRepository.getReferenceById(id);
        var enterprise = account.getEnterprise();

        return transactionService.withdrawal(account, enterprise, accountTransactionData);
    }
}

package com.cainant.testebackendtgid.controller;

import com.cainant.testebackendtgid.domain.Account;
import com.cainant.testebackendtgid.domain.Enterprise;
import com.cainant.testebackendtgid.dto.enterprise.EnterpriseRegisterData;
import com.cainant.testebackendtgid.dto.enterprise.EnterpriseCreatedData;
import com.cainant.testebackendtgid.dto.enterprise.EnterpriseListData;
import com.cainant.testebackendtgid.repository.AccountRepository;
import com.cainant.testebackendtgid.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<EnterpriseCreatedData> register(@RequestBody @Valid EnterpriseRegisterData registerData, UriComponentsBuilder uriBuilder) {
        var enterprise = new Enterprise(registerData);
        enterpriseRepository.save(enterprise);

        var uri = uriBuilder.path("enterprise/{id}").buildAndExpand(enterprise.getId()).toUri();

        return ResponseEntity.created(uri).body(new EnterpriseCreatedData(enterprise));
    }

    @GetMapping
    public ResponseEntity<Page<EnterpriseListData>> list(Pageable pagination) {
        var page = enterpriseRepository.findAll(pagination)
                .map(EnterpriseListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseListData> detail(@PathVariable Long id) {
        var enterprise = enterpriseRepository.getReferenceById(id);

        return ResponseEntity.ok(new EnterpriseListData(enterprise));
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<Page<Account>> getAccounts(@PathVariable Long id, Pageable pagination) {
        var accounts = accountRepository.findAccountsByEnterpriseId(pagination, id);

        return ResponseEntity.ok(accounts);
    }

}

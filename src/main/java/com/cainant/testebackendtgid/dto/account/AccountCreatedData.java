package com.cainant.testebackendtgid.dto.account;

import com.cainant.testebackendtgid.domain.Account;

public record AccountCreatedData(Long id,
                                 Float balance) {

    public AccountCreatedData(Account account) {
        this(account.getId(), account.getBalance());
    }
}

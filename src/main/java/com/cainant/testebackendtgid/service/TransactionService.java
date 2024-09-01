package com.cainant.testebackendtgid.service;

import com.cainant.testebackendtgid.domain.Account;
import com.cainant.testebackendtgid.domain.Enterprise;
import com.cainant.testebackendtgid.dto.account.AccountTransactionData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionService {

    public static ResponseEntity<Object> withdrawal(Account account, Enterprise enterprise, AccountTransactionData accountTransactionData) {
        var fee = enterprise.getFee() * accountTransactionData.value();
        var enterpriseBalance = enterprise.getBalance();
        var finalBalance = enterpriseBalance - (fee + accountTransactionData.value());
        if (finalBalance >= 0F) {
            if (account.getBalance() >= accountTransactionData.value()) {
                account.setBalance(account.getBalance() - accountTransactionData.value());
                enterprise.setBalance(finalBalance);

                // callback
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>("Insufficient user balance", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Insufficient enterprise balance", HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> deposit(Account account, Enterprise enterprise, AccountTransactionData accountTransactionData) {
        account.setBalance(account.getBalance() + accountTransactionData.value());

        var fee = enterprise.getFee() * accountTransactionData.value();
        var entepriseBalance = enterprise.getBalance();
        enterprise.setBalance(entepriseBalance + accountTransactionData.value() - fee);

        // callback
        return ResponseEntity.ok().build();
    }
}

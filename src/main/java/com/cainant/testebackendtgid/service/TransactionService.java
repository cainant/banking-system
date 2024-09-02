package com.cainant.testebackendtgid.service;

import com.cainant.testebackendtgid.domain.Account;
import com.cainant.testebackendtgid.domain.Enterprise;
import com.cainant.testebackendtgid.domain.Transaction;
import com.cainant.testebackendtgid.dto.TransactionType;
import com.cainant.testebackendtgid.dto.account.AccountTransactionData;
import com.cainant.testebackendtgid.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private WebhookExecutionService webhookExecutionService;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<Object> withdrawal(Account account, Enterprise enterprise, AccountTransactionData accountTransactionData) {
        var fee = enterprise.getFee() * accountTransactionData.value();
        var enterpriseBalance = enterprise.getBalance();
        var finalBalance = enterpriseBalance - (fee + accountTransactionData.value());
        if (finalBalance >= 0F) {
            if (account.getBalance() >= accountTransactionData.value()) {
                account.setBalance(account.getBalance() - accountTransactionData.value());
                enterprise.setBalance(finalBalance);

                var enterprisePayload = String.format("User %s has withdrawn R$ %.2f", account.getClient().getName(), accountTransactionData.value());
                webhookExecutionService.executeWebhook(enterprise.getWebhookURL(), enterprisePayload);

                var clientPayload = String.format("You have successfully withdrawn R$ %.2f from %s", accountTransactionData.value(), enterprise.getName());
                emailService.notifyUser(account.getClient().getName(), account.getEmail(), clientPayload);

                newTransaction(account, TransactionType.WITHDRAW, accountTransactionData.value());
                return ResponseEntity.ok().build();
            }
            return new ResponseEntity<>("Insufficient user balance", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Insufficient enterprise balance", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> deposit(Account account, Enterprise enterprise, AccountTransactionData accountTransactionData) {
        account.setBalance(account.getBalance() + accountTransactionData.value());

        var fee = enterprise.getFee() * accountTransactionData.value();
        var entepriseBalance = enterprise.getBalance();
        enterprise.setBalance(entepriseBalance + accountTransactionData.value() - fee);

        var enterprisePayload = String.format("User %s has deposited R$ %.2f", account.getClient().getName(), accountTransactionData.value());
        webhookExecutionService.executeWebhook(enterprise.getWebhookURL(), enterprisePayload);

        var clientPayload = String.format("You have successfully deposited R$ %.2f at %s", accountTransactionData.value(), enterprise.getName());
        emailService.notifyUser(account.getClient().getName(), account.getEmail(), clientPayload);

        newTransaction(account, TransactionType.DEPOSIT, accountTransactionData.value());
        return ResponseEntity.ok().build();
    }

    private void newTransaction(Account account, TransactionType transactionType, Float value) {
        var transaction = new Transaction(account, transactionType, value);
        repository.save(transaction);
    }
}

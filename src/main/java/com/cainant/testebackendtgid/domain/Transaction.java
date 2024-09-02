package com.cainant.testebackendtgid.domain;

import com.cainant.testebackendtgid.dto.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Float value;

    public Transaction(Account account, TransactionType transactionType, Float value) {
        this.account = account;
        this.type = transactionType;
        this.value = value;
    }
}

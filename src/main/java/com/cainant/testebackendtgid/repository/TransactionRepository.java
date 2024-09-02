package com.cainant.testebackendtgid.repository;

import com.cainant.testebackendtgid.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

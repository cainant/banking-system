package com.cainant.testebackendtgid.repository;

import com.cainant.testebackendtgid.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAccountsByEnterpriseId(Pageable pageable, Long id);
    Page<Account> findAccountsByClientId(Pageable pageable, Long id);
}

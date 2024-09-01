package com.cainant.testebackendtgid.repository;

import com.cainant.testebackendtgid.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "select c from Client c where c.cpf = ?1")
    Client findByCpf(String cpf);
}

package com.cainant.testebackendtgid.repository;

import com.cainant.testebackendtgid.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}

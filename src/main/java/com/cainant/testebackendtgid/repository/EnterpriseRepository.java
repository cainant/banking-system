package com.cainant.testebackendtgid.repository;

import com.cainant.testebackendtgid.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}

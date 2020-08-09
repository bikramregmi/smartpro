package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.Ledger;

import com.rubic.smartpro.service.dto.LedgerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Ledger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    Page<Ledger> findAllByCompanyId(Pageable pageable, Long id);
}

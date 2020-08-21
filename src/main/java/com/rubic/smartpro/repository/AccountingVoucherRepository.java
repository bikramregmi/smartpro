package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.AccountingVoucher;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AccountingVoucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountingVoucherRepository extends JpaRepository<AccountingVoucher, Long> {
    AccountingVoucher findByAccountName(String referenceNumber);
}

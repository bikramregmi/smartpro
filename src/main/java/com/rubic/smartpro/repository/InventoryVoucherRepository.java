package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.InventoryVoucher;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InventoryVoucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventoryVoucherRepository extends JpaRepository<InventoryVoucher, Long> {
    InventoryVoucher findByVoucherNumber(String voucherNumber);
}

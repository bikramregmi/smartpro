package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.InventoryVoucherEntry;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InventoryVoucherEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventoryVoucherEntryRepository extends JpaRepository<InventoryVoucherEntry, Long> {
    InventoryVoucherEntry findByVoucherNumber(String voucherNumber);
}

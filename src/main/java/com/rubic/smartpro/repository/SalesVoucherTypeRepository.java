package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.SalesVoucherType;

import com.rubic.smartpro.enumConstants.VoucherType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SalesVoucherType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesVoucherTypeRepository extends JpaRepository<SalesVoucherType, Long> {

    SalesVoucherType findByUniqueKeyAndReferenceNumberAndVoucherType(String uniqueKey, String referenceNumber, VoucherType voucherType);
}

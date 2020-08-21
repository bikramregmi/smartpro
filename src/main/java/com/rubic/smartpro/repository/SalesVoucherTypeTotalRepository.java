package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.SalesVoucherTypeTotal;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SalesVoucherTypeTotal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesVoucherTypeTotalRepository extends JpaRepository<SalesVoucherTypeTotal, Long> {

    SalesVoucherTypeTotal findByReferenceNumber(String referenceNumber);
}

package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.VoucherType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VoucherType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType, Long> {
}

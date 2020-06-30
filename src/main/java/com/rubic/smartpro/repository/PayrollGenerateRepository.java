package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.PayrollGenerate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PayrollGenerate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayrollGenerateRepository extends JpaRepository<PayrollGenerate, Long>, JpaSpecificationExecutor<PayrollGenerate> {
}

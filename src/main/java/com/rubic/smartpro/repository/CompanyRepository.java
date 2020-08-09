package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.Company;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findSelectedCompanyByExtraField(String status);
}

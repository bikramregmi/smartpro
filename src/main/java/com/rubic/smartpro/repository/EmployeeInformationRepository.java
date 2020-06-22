package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.EmployeeInformation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmployeeInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeInformationRepository extends JpaRepository<EmployeeInformation, Long> {
}

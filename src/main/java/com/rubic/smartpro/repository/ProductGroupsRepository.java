package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.ProductGroups;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductGroups entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductGroupsRepository extends JpaRepository<ProductGroups, Long> {
}

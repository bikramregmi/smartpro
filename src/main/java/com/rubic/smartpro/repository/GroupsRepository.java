package com.rubic.smartpro.repository;

import com.rubic.smartpro.domain.Groups;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Groups entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Page<Groups> findAllByCompanyId(Pageable pageable, Long id);
}

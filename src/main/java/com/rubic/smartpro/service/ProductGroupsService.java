package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.ProductGroupsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.ProductGroups}.
 */
public interface ProductGroupsService {

    /**
     * Save a productGroups.
     *
     * @param productGroupsDTO the entity to save.
     * @return the persisted entity.
     */
    ProductGroupsDTO save(ProductGroupsDTO productGroupsDTO);

    /**
     * Get all the productGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductGroupsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productGroups.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductGroupsDTO> findOne(Long id);

    /**
     * Delete the "id" productGroups.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

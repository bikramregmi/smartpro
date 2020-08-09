package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.ProductItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.ProductItem}.
 */
public interface ProductItemService {

    /**
     * Save a productItem.
     *
     * @param productItemDTO the entity to save.
     * @return the persisted entity.
     */
    ProductItemDTO save(ProductItemDTO productItemDTO);

    /**
     * Get all the productItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" productItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductItemDTO> findOne(Long id);

    /**
     * Delete the "id" productItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.UnitOfMeasureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.UnitOfMeasure}.
 */
public interface UnitOfMeasureService {

    /**
     * Save a unitOfMeasure.
     *
     * @param unitOfMeasureDTO the entity to save.
     * @return the persisted entity.
     */
    UnitOfMeasureDTO save(UnitOfMeasureDTO unitOfMeasureDTO);

    /**
     * Get all the unitOfMeasures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnitOfMeasureDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unitOfMeasure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnitOfMeasureDTO> findOne(Long id);

    /**
     * Delete the "id" unitOfMeasure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.PayrollGenerateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.PayrollGenerate}.
 */
public interface PayrollGenerateService {

    /**
     * Save a payrollGenerate.
     *
     * @param payrollGenerateDTO the entity to save.
     * @return the persisted entity.
     */
    PayrollGenerateDTO save(PayrollGenerateDTO payrollGenerateDTO);

    /**
     * Get all the payrollGenerates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayrollGenerateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" payrollGenerate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayrollGenerateDTO> findOne(Long id);

    /**
     * Delete the "id" payrollGenerate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.SalesVoucherTypeTotalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.SalesVoucherTypeTotal}.
 */
public interface SalesVoucherTypeTotalService {

    /**
     * Save a salesVoucherTypeTotal.
     *
     * @param salesVoucherTypeTotalDTO the entity to save.
     * @return the persisted entity.
     */
    SalesVoucherTypeTotalDTO save(SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO);

    /**
     * Get all the salesVoucherTypeTotals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesVoucherTypeTotalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesVoucherTypeTotal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesVoucherTypeTotalDTO> findOne(Long id);

    /**
     * Delete the "id" salesVoucherTypeTotal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

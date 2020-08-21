package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.SalesVoucherTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.SalesVoucherType}.
 */
public interface SalesVoucherTypeService {

    /**
     * Save a salesVoucherType.
     *
     * @param salesVoucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    SalesVoucherTypeDTO save(SalesVoucherTypeDTO salesVoucherTypeDTO);

    /**
     * Get all the salesVoucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesVoucherTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" salesVoucherType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesVoucherTypeDTO> findOne(Long id);

    /**
     * Delete the "id" salesVoucherType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    SalesVoucherTypeDTO findUniqueSalesVoucher (SalesVoucherTypeDTO salesVoucherTypeDTO);
}

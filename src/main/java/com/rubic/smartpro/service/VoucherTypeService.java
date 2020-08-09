package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.VoucherTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.VoucherType}.
 */
public interface VoucherTypeService {

    /**
     * Save a voucherType.
     *
     * @param voucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    VoucherTypeDTO save(VoucherTypeDTO voucherTypeDTO);

    /**
     * Get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VoucherTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" voucherType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VoucherTypeDTO> findOne(Long id);

    /**
     * Delete the "id" voucherType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

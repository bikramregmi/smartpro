package com.rubic.smartpro.service;

import com.rubic.smartpro.domain.InventoryVoucher;
import com.rubic.smartpro.service.dto.InventoryVoucherDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.InventoryVoucher}.
 */
public interface InventoryVoucherService {

    /**
     * Save a inventoryVoucher.
     *
     * @param inventoryVoucherDTO the entity to save.
     * @return the persisted entity.
     */
    InventoryVoucherDTO save(InventoryVoucherDTO inventoryVoucherDTO);

    /**
     * Get all the inventoryVouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InventoryVoucherDTO> findAll(Pageable pageable);


    /**
     * Get the "id" inventoryVoucher.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InventoryVoucherDTO> findOne(Long id);

    /**
     * Delete the "id" inventoryVoucher.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    InventoryVoucherDTO findByVoucherNumberId(String voucherNumber);
}

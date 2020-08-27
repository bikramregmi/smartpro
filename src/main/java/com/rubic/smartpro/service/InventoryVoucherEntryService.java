package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.InventoryVoucherEntryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.InventoryVoucherEntry}.
 */
public interface InventoryVoucherEntryService {

    /**
     * Save a inventoryVoucherEntry.
     *
     * @param inventoryVoucherEntryDTO the entity to save.
     * @return the persisted entity.
     */
    InventoryVoucherEntryDTO save(InventoryVoucherEntryDTO inventoryVoucherEntryDTO);

    /**
     * Get all the inventoryVoucherEntries.
     *
     * @return the list of entities.
     */
    List<InventoryVoucherEntryDTO> findAll();


    /**
     * Get the "id" inventoryVoucherEntry.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InventoryVoucherEntryDTO> findOne(Long id);

    /**
     * Delete the "id" inventoryVoucherEntry.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

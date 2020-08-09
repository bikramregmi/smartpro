package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.LedgerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.Ledger}.
 */
public interface LedgerService {

    /**
     * Save a ledger.
     *
     * @param ledgerDTO the entity to save.
     * @return the persisted entity.
     */
    LedgerDTO save(LedgerDTO ledgerDTO);

    /**
     * Get all the ledgers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LedgerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ledger.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LedgerDTO> findOne(Long id);

    /**
     * Delete the "id" ledger.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

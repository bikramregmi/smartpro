package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.AccountingVoucherDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.AccountingVoucher}.
 */
public interface AccountingVoucherService {

    /**
     * Save a accountingVoucher.
     *
     * @param accountingVoucherDTO the entity to save.
     * @return the persisted entity.
     */
    AccountingVoucherDTO save(AccountingVoucherDTO accountingVoucherDTO);

    /**
     * Get all the accountingVouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountingVoucherDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountingVoucher.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountingVoucherDTO> findOne(Long id);

    /**
     * Delete the "id" accountingVoucher.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

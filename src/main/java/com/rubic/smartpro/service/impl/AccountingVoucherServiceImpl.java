package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.enumConstants.AccountingVoucherType;
import com.rubic.smartpro.enumConstants.VoucherTypeLedger;
import com.rubic.smartpro.service.AccountingVoucherService;
import com.rubic.smartpro.domain.AccountingVoucher;
import com.rubic.smartpro.repository.AccountingVoucherRepository;
import com.rubic.smartpro.service.dto.AccountingVoucherDTO;
import com.rubic.smartpro.service.mapper.AccountingVoucherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountingVoucher}.
 */
@Service
@Transactional
public class AccountingVoucherServiceImpl implements AccountingVoucherService {

    private final Logger log = LoggerFactory.getLogger(AccountingVoucherServiceImpl.class);

    private final AccountingVoucherRepository accountingVoucherRepository;

    private final AccountingVoucherMapper accountingVoucherMapper;

    public AccountingVoucherServiceImpl(AccountingVoucherRepository accountingVoucherRepository, AccountingVoucherMapper accountingVoucherMapper) {
        this.accountingVoucherRepository = accountingVoucherRepository;
        this.accountingVoucherMapper = accountingVoucherMapper;
    }

    /**
     * Save a accountingVoucher.
     *
     * @param accountingVoucherDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountingVoucherDTO save(AccountingVoucherDTO accountingVoucherDTO) {
        log.debug("Request to save AccountingVoucher : {}", accountingVoucherDTO);
        if(Objects.equals(accountingVoucherDTO.getAccountingVoucherType(), "Sales"))
            accountingVoucherDTO.setVoucherTypeLedger(VoucherTypeLedger.SalesLedger.toString());
        else if(Objects.equals(accountingVoucherDTO.getAccountingVoucherType(), "Buy"))
            accountingVoucherDTO.setVoucherTypeLedger(VoucherTypeLedger.BuyLedger.toString());
        AccountingVoucher accountingVoucher = accountingVoucherMapper.toEntity(accountingVoucherDTO);
        accountingVoucher = accountingVoucherRepository.save(accountingVoucher);
        return accountingVoucherMapper.toDto(accountingVoucher);
    }
    /**
     * Get all the accountingVouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountingVoucherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountingVouchers");
        return accountingVoucherRepository.findAll(pageable)
            .map(accountingVoucherMapper::toDto);
    }


    /**
     * Get one accountingVoucher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountingVoucherDTO> findOne(Long id) {
        log.debug("Request to get AccountingVoucher : {}", id);
        return accountingVoucherRepository.findById(id)
            .map(accountingVoucherMapper::toDto);
    }

    /**
     * Delete the accountingVoucher by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountingVoucher : {}", id);
        accountingVoucherRepository.deleteById(id);
    }
}

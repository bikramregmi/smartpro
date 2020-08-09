package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import com.rubic.smartpro.service.LedgerService;
import com.rubic.smartpro.domain.Ledger;
import com.rubic.smartpro.repository.LedgerRepository;
import com.rubic.smartpro.service.dto.LedgerDTO;
import com.rubic.smartpro.service.mapper.LedgerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ledger}.
 */
@Service
@Transactional
public class LedgerServiceImpl implements LedgerService {

    private final Logger log = LoggerFactory.getLogger(LedgerServiceImpl.class);

    private final LedgerRepository ledgerRepository;

    private final LedgerMapper ledgerMapper;

    private final CompanyRepository companyRepository;

    public LedgerServiceImpl(LedgerRepository ledgerRepository, LedgerMapper ledgerMapper, CompanyRepository companyRepository) {
        this.ledgerRepository = ledgerRepository;
        this.ledgerMapper = ledgerMapper;
        this.companyRepository = companyRepository;
    }

    /**
     * Save a ledger.
     *
     * @param ledgerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LedgerDTO save(LedgerDTO ledgerDTO) {
        log.debug("Request to save Ledger : {}", ledgerDTO);
        Optional<Company> company = companyRepository.findSelectedCompanyByExtraField("true");
        Ledger ledger = ledgerMapper.toEntity(ledgerDTO);
        ledger.setCompany(company.get());
        ledger = ledgerRepository.save(ledger);
        return ledgerMapper.toDto(ledger);
    }

    /**
     * Get all the ledgers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LedgerDTO> findAll(Pageable pageable) {
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        log.debug("Request to get all Ledgers");
        return ledgerRepository.findAllByCompanyId(pageable,company.get().getId())
            .map(ledgerMapper::toDto);
    }


    /**
     * Get one ledger by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LedgerDTO> findOne(Long id) {
        log.debug("Request to get Ledger : {}", id);
        return ledgerRepository.findById(id)
            .map(ledgerMapper::toDto);
    }

    /**
     * Delete the ledger by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ledger : {}", id);
        ledgerRepository.deleteById(id);
    }
}

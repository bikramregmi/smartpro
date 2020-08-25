package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.AccountingVoucher;
import com.rubic.smartpro.domain.SalesVoucherTypeTotal;
import com.rubic.smartpro.enumConstants.VoucherType;
import com.rubic.smartpro.repository.AccountingVoucherRepository;
import com.rubic.smartpro.repository.SalesVoucherTypeTotalRepository;
import com.rubic.smartpro.service.SalesVoucherTypeService;
import com.rubic.smartpro.domain.SalesVoucherType;
import com.rubic.smartpro.repository.SalesVoucherTypeRepository;
import com.rubic.smartpro.service.dto.SalesVoucherTypeDTO;
import com.rubic.smartpro.service.mapper.SalesVoucherTypeMapper;
import com.rubic.smartpro.service.mapper.SalesVoucherTypeTotalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SalesVoucherType}.
 */
@Service
@Transactional
public class SalesVoucherTypeServiceImpl implements SalesVoucherTypeService {

    private final Logger log = LoggerFactory.getLogger(SalesVoucherTypeServiceImpl.class);

    private final SalesVoucherTypeRepository salesVoucherTypeRepository;

    private final SalesVoucherTypeMapper salesVoucherTypeMapper;

    private final SalesVoucherTypeTotalRepository salesVoucherTypeTotalRepository;

    private final SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper;

    private final AccountingVoucherRepository accountingVoucherRepository;


    public SalesVoucherTypeServiceImpl(SalesVoucherTypeRepository salesVoucherTypeRepository, SalesVoucherTypeMapper salesVoucherTypeMapper, SalesVoucherTypeTotalRepository salesVoucherTypeTotalRepository, SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper, AccountingVoucherRepository accountingVoucherRepository) {
        this.salesVoucherTypeRepository = salesVoucherTypeRepository;
        this.salesVoucherTypeMapper = salesVoucherTypeMapper;
        this.salesVoucherTypeTotalRepository = salesVoucherTypeTotalRepository;
        this.salesVoucherTypeTotalMapper = salesVoucherTypeTotalMapper;
        this.accountingVoucherRepository = accountingVoucherRepository;
    }

    /**
     * Save a salesVoucherType.
     *
     * @param salesVoucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SalesVoucherTypeDTO save(SalesVoucherTypeDTO salesVoucherTypeDTO) {
        log.debug("Request to save SalesVoucherType : {}", salesVoucherTypeDTO);
        AccountingVoucher accountingVoucher = accountingVoucherRepository.findByAccountName(salesVoucherTypeDTO.getReferenceNumber());
        SalesVoucherType salesVoucherType = salesVoucherTypeMapper.toEntity(salesVoucherTypeDTO);
        salesVoucherType.setSalesVoucherTypeTotal(checkTotal(salesVoucherTypeDTO));
        if (accountingVoucher != null)
            salesVoucherType.setAccountingVoucher(accountingVoucher);
        salesVoucherType = salesVoucherTypeRepository.save(salesVoucherType);
        return salesVoucherTypeMapper.toDto(salesVoucherType);
    }

    public SalesVoucherTypeTotal checkTotal(SalesVoucherTypeDTO salesVoucherTypeDTO) {
        SalesVoucherTypeTotal salesVoucherTypeTotal = salesVoucherTypeTotalRepository.findByReferenceNumber(salesVoucherTypeDTO.getReferenceNumber());
        if (salesVoucherTypeTotal != null) {
            if (salesVoucherTypeDTO.getId() != null) {
                SalesVoucherType salesVoucherType = salesVoucherTypeMapper.toEntity(findUniqueSalesVoucher(salesVoucherTypeDTO));
                if (salesVoucherTypeDTO.getVoucherType().equals("Journal")) {
                    if (salesVoucherTypeDTO.getDebit() != null) {
                        if(salesVoucherTypeTotal.getCreditTotal()==null)
                            salesVoucherTypeTotal.setCreditTotal(0.0);
                        salesVoucherTypeTotal.setDebitTotal(salesVoucherTypeDTO.getDebit() + (salesVoucherTypeTotal.getDebitTotal() - salesVoucherType.getDebit()));
                    }
                    if (salesVoucherTypeDTO.getCredit() != null) {
                        if (salesVoucherTypeTotal.getCreditTotal() == null)
                            salesVoucherTypeTotal.setCreditTotal(0.0);
                        salesVoucherTypeTotal.setCreditTotal(salesVoucherTypeDTO.getCredit() + (salesVoucherTypeTotal.getCreditTotal() - salesVoucherType.getCredit()));
                    }
                } else
                    salesVoucherTypeTotal.setAmountTotal(salesVoucherTypeDTO.getAmount() + (salesVoucherTypeTotal.getAmountTotal() - salesVoucherType.getAmount()));
                if (salesVoucherTypeDTO.getQuantity() != null)
                    salesVoucherTypeTotal.setQuantityTotal(salesVoucherTypeDTO.getQuantity() + (salesVoucherTypeTotal.getQuantityTotal() - salesVoucherType.getQuantity()));
                if (salesVoucherTypeDTO.getRate() != null)
                    salesVoucherTypeTotal.setRateTotal(salesVoucherTypeDTO.getRate() + (salesVoucherTypeTotal.getRateTotal() - salesVoucherType.getRate()));
            } else {
                if (salesVoucherTypeDTO.getVoucherType().equals("Journal")) {
                    if (salesVoucherTypeDTO.getDebit() != null) {
                        if (salesVoucherTypeTotal.getDebitTotal() == null)
                            salesVoucherTypeTotal.setDebitTotal(0.0);
                        salesVoucherTypeTotal.setDebitTotal(salesVoucherTypeDTO.getDebit() + salesVoucherTypeTotal.getDebitTotal());
                    }
                    if (salesVoucherTypeDTO.getCredit() != null) {
                        if (salesVoucherTypeTotal.getCreditTotal() == null)
                            salesVoucherTypeTotal.setCreditTotal(0.0);
                        salesVoucherTypeTotal.setCreditTotal(salesVoucherTypeDTO.getCredit() + salesVoucherTypeTotal.getCreditTotal());
                    }
                } else
                    salesVoucherTypeTotal.setAmountTotal(salesVoucherTypeTotal.getAmountTotal() + salesVoucherTypeDTO.getAmount());
                if (salesVoucherTypeDTO.getQuantity() != null)
                    salesVoucherTypeTotal.setQuantityTotal(salesVoucherTypeTotal.getQuantityTotal() + salesVoucherTypeDTO.getQuantity());
                if (salesVoucherTypeDTO.getRate() != null)
                    salesVoucherTypeTotal.setRateTotal(salesVoucherTypeTotal.getRateTotal() + salesVoucherTypeDTO.getRate());
            }
        } else {
            salesVoucherTypeTotal = new SalesVoucherTypeTotal();
            if (salesVoucherTypeDTO.getVoucherType().equals("Journal")) {
                if (salesVoucherTypeDTO.getDebit() != null) {
                    if (salesVoucherTypeTotal.getDebitTotal() == null)
                        salesVoucherTypeTotal.setDebitTotal(0.0);
                    salesVoucherTypeTotal.setDebitTotal(salesVoucherTypeDTO.getDebit());
                }
                if (salesVoucherTypeDTO.getCredit() != null) {
                    if (salesVoucherTypeTotal.getCreditTotal() == null)
                        salesVoucherTypeTotal.setCreditTotal(0.0);
                    salesVoucherTypeTotal.setCreditTotal(salesVoucherTypeDTO.getCredit());
                }
            } else
                salesVoucherTypeTotal.setAmountTotal(salesVoucherTypeDTO.getAmount());
            if (salesVoucherTypeDTO.getQuantity() != null)
                salesVoucherTypeTotal.setQuantityTotal(salesVoucherTypeDTO.getQuantity());
            if (salesVoucherTypeDTO.getRate() != null)
                salesVoucherTypeTotal.setRateTotal(salesVoucherTypeDTO.getRate());
        }
        salesVoucherTypeTotal.setReferenceNumber(salesVoucherTypeDTO.getReferenceNumber());
        return salesVoucherTypeTotalRepository.save(salesVoucherTypeTotal);
    }

    /**
     * Get all the salesVoucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesVoucherTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesVoucherTypes");
        return salesVoucherTypeRepository.findAll(pageable)
            .map(salesVoucherTypeMapper::toDto);
    }


    /**
     * Get one salesVoucherType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesVoucherTypeDTO> findOne(Long id) {
        log.debug("Request to get SalesVoucherType : {}", id);
        return salesVoucherTypeRepository.findById(id)
            .map(salesVoucherTypeMapper::toDto);
    }

    /**
     * Delete the salesVoucherType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesVoucherType : {}", id);
        salesVoucherTypeRepository.deleteById(id);
    }

    @Override
    public SalesVoucherTypeDTO findUniqueSalesVoucher(SalesVoucherTypeDTO salesVoucherTypeDTO) {
        return salesVoucherTypeMapper.toDto(salesVoucherTypeRepository.findByUniqueKeyAndReferenceNumberAndVoucherType(salesVoucherTypeDTO.getUniqueKey(), salesVoucherTypeDTO.getReferenceNumber(), VoucherType.valueOf(salesVoucherTypeDTO.getVoucherType())));
    }
}

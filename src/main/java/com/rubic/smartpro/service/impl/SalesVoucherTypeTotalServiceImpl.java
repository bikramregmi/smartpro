package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.SalesVoucherTypeTotalService;
import com.rubic.smartpro.domain.SalesVoucherTypeTotal;
import com.rubic.smartpro.repository.SalesVoucherTypeTotalRepository;
import com.rubic.smartpro.service.dto.SalesVoucherTypeTotalDTO;
import com.rubic.smartpro.service.mapper.SalesVoucherTypeTotalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SalesVoucherTypeTotal}.
 */
@Service
@Transactional
public class SalesVoucherTypeTotalServiceImpl implements SalesVoucherTypeTotalService {

    private final Logger log = LoggerFactory.getLogger(SalesVoucherTypeTotalServiceImpl.class);

    private final SalesVoucherTypeTotalRepository salesVoucherTypeTotalRepository;

    private final SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper;

    public SalesVoucherTypeTotalServiceImpl(SalesVoucherTypeTotalRepository salesVoucherTypeTotalRepository, SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper) {
        this.salesVoucherTypeTotalRepository = salesVoucherTypeTotalRepository;
        this.salesVoucherTypeTotalMapper = salesVoucherTypeTotalMapper;
    }

    /**
     * Save a salesVoucherTypeTotal.
     *
     * @param salesVoucherTypeTotalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SalesVoucherTypeTotalDTO save(SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO) {
        log.debug("Request to save SalesVoucherTypeTotal : {}", salesVoucherTypeTotalDTO);
        SalesVoucherTypeTotal salesVoucherTypeTotal = salesVoucherTypeTotalMapper.toEntity(salesVoucherTypeTotalDTO);
        salesVoucherTypeTotal = salesVoucherTypeTotalRepository.save(salesVoucherTypeTotal);
        return salesVoucherTypeTotalMapper.toDto(salesVoucherTypeTotal);
    }

    /**
     * Get all the salesVoucherTypeTotals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SalesVoucherTypeTotalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesVoucherTypeTotals");
        return salesVoucherTypeTotalRepository.findAll(pageable)
            .map(salesVoucherTypeTotalMapper::toDto);
    }


    /**
     * Get one salesVoucherTypeTotal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SalesVoucherTypeTotalDTO> findOne(Long id) {
        log.debug("Request to get SalesVoucherTypeTotal : {}", id);
        return salesVoucherTypeTotalRepository.findById(id)
            .map(salesVoucherTypeTotalMapper::toDto);
    }

    /**
     * Delete the salesVoucherTypeTotal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesVoucherTypeTotal : {}", id);
        salesVoucherTypeTotalRepository.deleteById(id);
    }
}

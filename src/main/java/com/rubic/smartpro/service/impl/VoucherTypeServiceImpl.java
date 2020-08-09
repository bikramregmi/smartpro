package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.VoucherTypeService;
import com.rubic.smartpro.domain.VoucherType;
import com.rubic.smartpro.repository.VoucherTypeRepository;
import com.rubic.smartpro.service.dto.VoucherTypeDTO;
import com.rubic.smartpro.service.mapper.VoucherTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VoucherType}.
 */
@Service
@Transactional
public class VoucherTypeServiceImpl implements VoucherTypeService {

    private final Logger log = LoggerFactory.getLogger(VoucherTypeServiceImpl.class);

    private final VoucherTypeRepository voucherTypeRepository;

    private final VoucherTypeMapper voucherTypeMapper;

    public VoucherTypeServiceImpl(VoucherTypeRepository voucherTypeRepository, VoucherTypeMapper voucherTypeMapper) {
        this.voucherTypeRepository = voucherTypeRepository;
        this.voucherTypeMapper = voucherTypeMapper;
    }

    /**
     * Save a voucherType.
     *
     * @param voucherTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VoucherTypeDTO save(VoucherTypeDTO voucherTypeDTO) {
        log.debug("Request to save VoucherType : {}", voucherTypeDTO);
        VoucherType voucherType = voucherTypeMapper.toEntity(voucherTypeDTO);
        voucherType = voucherTypeRepository.save(voucherType);
        return voucherTypeMapper.toDto(voucherType);
    }

    /**
     * Get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VoucherTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VoucherTypes");
        return voucherTypeRepository.findAll(pageable)
            .map(voucherTypeMapper::toDto);
    }


    /**
     * Get one voucherType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VoucherTypeDTO> findOne(Long id) {
        log.debug("Request to get VoucherType : {}", id);
        return voucherTypeRepository.findById(id)
            .map(voucherTypeMapper::toDto);
    }

    /**
     * Delete the voucherType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VoucherType : {}", id);
        voucherTypeRepository.deleteById(id);
    }
}

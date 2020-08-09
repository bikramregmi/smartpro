package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.UnitOfMeasureService;
import com.rubic.smartpro.domain.UnitOfMeasure;
import com.rubic.smartpro.repository.UnitOfMeasureRepository;
import com.rubic.smartpro.service.dto.UnitOfMeasureDTO;
import com.rubic.smartpro.service.mapper.UnitOfMeasureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnitOfMeasure}.
 */
@Service
@Transactional
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final Logger log = LoggerFactory.getLogger(UnitOfMeasureServiceImpl.class);

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final UnitOfMeasureMapper unitOfMeasureMapper;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureMapper unitOfMeasureMapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureMapper = unitOfMeasureMapper;
    }

    /**
     * Save a unitOfMeasure.
     *
     * @param unitOfMeasureDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnitOfMeasureDTO save(UnitOfMeasureDTO unitOfMeasureDTO) {
        log.debug("Request to save UnitOfMeasure : {}", unitOfMeasureDTO);
        UnitOfMeasure unitOfMeasure = unitOfMeasureMapper.toEntity(unitOfMeasureDTO);
        unitOfMeasure = unitOfMeasureRepository.save(unitOfMeasure);
        return unitOfMeasureMapper.toDto(unitOfMeasure);
    }

    /**
     * Get all the unitOfMeasures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UnitOfMeasureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnitOfMeasures");
        return unitOfMeasureRepository.findAll(pageable)
            .map(unitOfMeasureMapper::toDto);
    }


    /**
     * Get one unitOfMeasure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitOfMeasureDTO> findOne(Long id) {
        log.debug("Request to get UnitOfMeasure : {}", id);
        return unitOfMeasureRepository.findById(id)
            .map(unitOfMeasureMapper::toDto);
    }

    /**
     * Delete the unitOfMeasure by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UnitOfMeasure : {}", id);
        unitOfMeasureRepository.deleteById(id);
    }
}

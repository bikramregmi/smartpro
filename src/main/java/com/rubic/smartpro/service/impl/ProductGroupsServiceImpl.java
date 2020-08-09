package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.ProductGroupsService;
import com.rubic.smartpro.domain.ProductGroups;
import com.rubic.smartpro.repository.ProductGroupsRepository;
import com.rubic.smartpro.service.dto.ProductGroupsDTO;
import com.rubic.smartpro.service.mapper.ProductGroupsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductGroups}.
 */
@Service
@Transactional
public class ProductGroupsServiceImpl implements ProductGroupsService {

    private final Logger log = LoggerFactory.getLogger(ProductGroupsServiceImpl.class);

    private final ProductGroupsRepository productGroupsRepository;

    private final ProductGroupsMapper productGroupsMapper;

    public ProductGroupsServiceImpl(ProductGroupsRepository productGroupsRepository, ProductGroupsMapper productGroupsMapper) {
        this.productGroupsRepository = productGroupsRepository;
        this.productGroupsMapper = productGroupsMapper;
    }

    /**
     * Save a productGroups.
     *
     * @param productGroupsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductGroupsDTO save(ProductGroupsDTO productGroupsDTO) {
        log.debug("Request to save ProductGroups : {}", productGroupsDTO);
        ProductGroups productGroups = productGroupsMapper.toEntity(productGroupsDTO);
        productGroups = productGroupsRepository.save(productGroups);
        return productGroupsMapper.toDto(productGroups);
    }

    /**
     * Get all the productGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductGroupsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductGroups");
        return productGroupsRepository.findAll(pageable)
            .map(productGroupsMapper::toDto);
    }


    /**
     * Get one productGroups by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductGroupsDTO> findOne(Long id) {
        log.debug("Request to get ProductGroups : {}", id);
        return productGroupsRepository.findById(id)
            .map(productGroupsMapper::toDto);
    }

    /**
     * Delete the productGroups by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductGroups : {}", id);
        productGroupsRepository.deleteById(id);
    }
}

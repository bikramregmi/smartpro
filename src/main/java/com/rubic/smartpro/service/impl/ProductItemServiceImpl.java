package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.ProductItemService;
import com.rubic.smartpro.domain.ProductItem;
import com.rubic.smartpro.repository.ProductItemRepository;
import com.rubic.smartpro.service.dto.ProductItemDTO;
import com.rubic.smartpro.service.mapper.ProductItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductItem}.
 */
@Service
@Transactional
public class ProductItemServiceImpl implements ProductItemService {

    private final Logger log = LoggerFactory.getLogger(ProductItemServiceImpl.class);

    private final ProductItemRepository productItemRepository;

    private final ProductItemMapper productItemMapper;

    public ProductItemServiceImpl(ProductItemRepository productItemRepository, ProductItemMapper productItemMapper) {
        this.productItemRepository = productItemRepository;
        this.productItemMapper = productItemMapper;
    }

    /**
     * Save a productItem.
     *
     * @param productItemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProductItemDTO save(ProductItemDTO productItemDTO) {
        log.debug("Request to save ProductItem : {}", productItemDTO);
        ProductItem productItem = productItemMapper.toEntity(productItemDTO);
        productItem = productItemRepository.save(productItem);
        return productItemMapper.toDto(productItem);
    }

    /**
     * Get all the productItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProductItems");
        return productItemRepository.findAll(pageable)
            .map(productItemMapper::toDto);
    }


    /**
     * Get one productItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductItemDTO> findOne(Long id) {
        log.debug("Request to get ProductItem : {}", id);
        return productItemRepository.findById(id)
            .map(productItemMapper::toDto);
    }

    /**
     * Delete the productItem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductItem : {}", id);
        productItemRepository.deleteById(id);
    }
}

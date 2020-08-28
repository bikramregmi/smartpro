package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.InventoryVoucherService;
import com.rubic.smartpro.domain.InventoryVoucher;
import com.rubic.smartpro.repository.InventoryVoucherRepository;
import com.rubic.smartpro.service.SelectedCompany;
import com.rubic.smartpro.service.dto.InventoryVoucherDTO;
import com.rubic.smartpro.service.mapper.InventoryVoucherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Service Implementation for managing {@link InventoryVoucher}.
 */
@Service
@Transactional
public class InventoryVoucherServiceImpl implements InventoryVoucherService {

    private final Logger log = LoggerFactory.getLogger(InventoryVoucherServiceImpl.class);

    private final InventoryVoucherRepository inventoryVoucherRepository;

    private final InventoryVoucherMapper inventoryVoucherMapper;

    private final SelectedCompany selectedCompany;

    public InventoryVoucherServiceImpl(InventoryVoucherRepository inventoryVoucherRepository, InventoryVoucherMapper inventoryVoucherMapper, SelectedCompany selectedCompany) {
        this.inventoryVoucherRepository = inventoryVoucherRepository;
        this.inventoryVoucherMapper = inventoryVoucherMapper;
        this.selectedCompany = selectedCompany;
    }

    /**
     * Save a inventoryVoucher.
     *
     * @param inventoryVoucherDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InventoryVoucherDTO save(InventoryVoucherDTO inventoryVoucherDTO) {
        log.debug("Request to save InventoryVoucher : {}", inventoryVoucherDTO);
        InventoryVoucher inventoryVoucher;
        if (inventoryVoucherDTO.getId() == null)
            inventoryVoucherDTO.setVoucherNumber(new Date().toInstant().toString());
        else {
            inventoryVoucher = inventoryVoucherRepository.findByVoucherNumber(inventoryVoucherDTO.getVoucherNumber());
        }
        inventoryVoucher = inventoryVoucherMapper.toEntity(inventoryVoucherDTO);
        inventoryVoucher.setCompany(selectedCompany.getSelectedCompany());
        inventoryVoucher = inventoryVoucherRepository.save(inventoryVoucher);
        return inventoryVoucherMapper.toDto(inventoryVoucher);
    }

    /**
     * Get all the inventoryVouchers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InventoryVoucherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InventoryVouchers");
        return inventoryVoucherRepository.findAll(pageable)
            .map(inventoryVoucherMapper::toDto);
    }


    /**
     * Get one inventoryVoucher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InventoryVoucherDTO> findOne(Long id) {
        log.debug("Request to get InventoryVoucher : {}", id);
        return inventoryVoucherRepository.findById(id)
            .map(inventoryVoucherMapper::toDto);
    }

    /**
     * Delete the inventoryVoucher by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InventoryVoucher : {}", id);
        inventoryVoucherRepository.deleteById(id);
    }

    @Override
    public InventoryVoucherDTO findByVoucherNumberId(String voucherNumber) {
        return inventoryVoucherMapper.toDto(inventoryVoucherRepository.findByVoucherNumber(voucherNumber));
    }
}

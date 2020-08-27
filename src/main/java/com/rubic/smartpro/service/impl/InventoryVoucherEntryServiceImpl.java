package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.InventoryVoucher;
import com.rubic.smartpro.repository.InventoryVoucherRepository;
import com.rubic.smartpro.service.InventoryVoucherEntryService;
import com.rubic.smartpro.domain.InventoryVoucherEntry;
import com.rubic.smartpro.repository.InventoryVoucherEntryRepository;
import com.rubic.smartpro.service.dto.InventoryVoucherEntryDTO;
import com.rubic.smartpro.service.mapper.InventoryVoucherEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InventoryVoucherEntry}.
 */
@Service
@Transactional
public class InventoryVoucherEntryServiceImpl implements InventoryVoucherEntryService {

    private final Logger log = LoggerFactory.getLogger(InventoryVoucherEntryServiceImpl.class);

    private final InventoryVoucherEntryRepository inventoryVoucherEntryRepository;

    private final InventoryVoucherEntryMapper inventoryVoucherEntryMapper;

    private final InventoryVoucherRepository inventoryVoucherRepository;

    public InventoryVoucherEntryServiceImpl(InventoryVoucherEntryRepository inventoryVoucherEntryRepository, InventoryVoucherEntryMapper inventoryVoucherEntryMapper, InventoryVoucherRepository inventoryVoucherRepository) {
        this.inventoryVoucherEntryRepository = inventoryVoucherEntryRepository;
        this.inventoryVoucherEntryMapper = inventoryVoucherEntryMapper;
        this.inventoryVoucherRepository = inventoryVoucherRepository;
    }

    /**
     * Save a inventoryVoucherEntry.
     *
     * @param inventoryVoucherEntryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InventoryVoucherEntryDTO save(InventoryVoucherEntryDTO inventoryVoucherEntryDTO) {
        log.debug("Request to save InventoryVoucherEntry : {}", inventoryVoucherEntryDTO);
        InventoryVoucherEntry inventoryVoucherEntry = new InventoryVoucherEntry();
        InventoryVoucher inventoryVoucher = new InventoryVoucher();
        if (inventoryVoucherEntryDTO.getVoucherNumber() == null)
            inventoryVoucherEntryDTO.setVoucherNumber(new Date().toInstant().toString());
        inventoryVoucherEntry = inventoryVoucherEntryMapper.toEntity(inventoryVoucherEntryDTO);
        InventoryVoucher inventoryVoucher1 = inventoryVoucherRepository.findByVoucherNumber(inventoryVoucherEntryDTO.getVoucherNumber());
        if (inventoryVoucher1 != null) {
            if (inventoryVoucherEntryDTO.getSrAmount() != null) {
                inventoryVoucher1.setSrQuantityTotal(inventoryVoucherEntryDTO.getSrQuantity() + inventoryVoucher1.getSrQuantityTotal());
                inventoryVoucher1.setSrAmountTotal(inventoryVoucherEntryDTO.getSrAmount() + inventoryVoucher1.getSrAmountTotal());
            } else if (inventoryVoucherEntryDTO.getDesAmount() != null) {
                inventoryVoucher1.setSrQuantityTotal(inventoryVoucher1.getSrQuantityTotal());
                inventoryVoucher1.setSrAmountTotal(inventoryVoucher1.getSrAmountTotal());
                if (inventoryVoucher1.getDesQuantityTotal() != null && inventoryVoucher1.getDesAmountTotal() != null) {
                    inventoryVoucher1.setDesQuantityTotal(inventoryVoucherEntryDTO.getDesQuantity() + inventoryVoucher1.getDesQuantityTotal());
                    inventoryVoucher1.setDesAmountTotal(inventoryVoucherEntryDTO.getDesAmount() + inventoryVoucher1.getDesAmountTotal());
                } else {
                    inventoryVoucher1.setDesQuantityTotal(inventoryVoucherEntryDTO.getDesQuantity());
                    inventoryVoucher1.setDesAmountTotal(inventoryVoucherEntryDTO.getDesAmount());
                }
            }
            inventoryVoucherEntry.setInventoryVoucher(inventoryVoucherRepository.save(inventoryVoucher1));
        } else {
            inventoryVoucher.setVoucherNumber(inventoryVoucherEntryDTO.getVoucherNumber());
            if (inventoryVoucherEntryDTO.getSrAmount() != null) {
                inventoryVoucher.setSrQuantityTotal(inventoryVoucherEntryDTO.getSrQuantity());
                inventoryVoucher.setSrAmountTotal(inventoryVoucherEntryDTO.getSrAmount());
            }
            inventoryVoucherEntry.setInventoryVoucher(inventoryVoucherRepository.save(inventoryVoucher));
        }
        inventoryVoucherEntry = inventoryVoucherEntryRepository.save(inventoryVoucherEntry);
        return inventoryVoucherEntryMapper.toDto(inventoryVoucherEntry);
    }

    /**
     * Get all the inventoryVoucherEntries.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InventoryVoucherEntryDTO> findAll() {
        log.debug("Request to get all InventoryVoucherEntries");
        return inventoryVoucherEntryRepository.findAll().stream()
            .map(inventoryVoucherEntryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one inventoryVoucherEntry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InventoryVoucherEntryDTO> findOne(Long id) {
        log.debug("Request to get InventoryVoucherEntry : {}", id);
        return inventoryVoucherEntryRepository.findById(id)
            .map(inventoryVoucherEntryMapper::toDto);
    }

    /**
     * Delete the inventoryVoucherEntry by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InventoryVoucherEntry : {}", id);
        inventoryVoucherEntryRepository.deleteById(id);
    }
}

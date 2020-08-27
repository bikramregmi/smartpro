package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.InventoryVoucherEntryService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.InventoryVoucherEntryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rubic.smartpro.domain.InventoryVoucherEntry}.
 */
@RestController
@RequestMapping("/api")
public class InventoryVoucherEntryResource {

    private final Logger log = LoggerFactory.getLogger(InventoryVoucherEntryResource.class);

    private static final String ENTITY_NAME = "inventoryVoucherEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventoryVoucherEntryService inventoryVoucherEntryService;

    public InventoryVoucherEntryResource(InventoryVoucherEntryService inventoryVoucherEntryService) {
        this.inventoryVoucherEntryService = inventoryVoucherEntryService;
    }

    /**
     * {@code POST  /inventory-voucher-entries} : Create a new inventoryVoucherEntry.
     *
     * @param inventoryVoucherEntryDTO the inventoryVoucherEntryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventoryVoucherEntryDTO, or with status {@code 400 (Bad Request)} if the inventoryVoucherEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inventory-voucher-entries")
    public ResponseEntity<InventoryVoucherEntryDTO> createInventoryVoucherEntry(@RequestBody InventoryVoucherEntryDTO inventoryVoucherEntryDTO) throws URISyntaxException {
        log.debug("REST request to save InventoryVoucherEntry : {}", inventoryVoucherEntryDTO);
        if (inventoryVoucherEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new inventoryVoucherEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InventoryVoucherEntryDTO result = inventoryVoucherEntryService.save(inventoryVoucherEntryDTO);
        return ResponseEntity.created(new URI("/api/inventory-voucher-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inventory-voucher-entries} : Updates an existing inventoryVoucherEntry.
     *
     * @param inventoryVoucherEntryDTO the inventoryVoucherEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryVoucherEntryDTO,
     * or with status {@code 400 (Bad Request)} if the inventoryVoucherEntryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventoryVoucherEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inventory-voucher-entries")
    public ResponseEntity<InventoryVoucherEntryDTO> updateInventoryVoucherEntry(@RequestBody InventoryVoucherEntryDTO inventoryVoucherEntryDTO) throws URISyntaxException {
        log.debug("REST request to update InventoryVoucherEntry : {}", inventoryVoucherEntryDTO);
        if (inventoryVoucherEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InventoryVoucherEntryDTO result = inventoryVoucherEntryService.save(inventoryVoucherEntryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, inventoryVoucherEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inventory-voucher-entries} : get all the inventoryVoucherEntries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventoryVoucherEntries in body.
     */
    @GetMapping("/inventory-voucher-entries")
    public List<InventoryVoucherEntryDTO> getAllInventoryVoucherEntries() {
        log.debug("REST request to get all InventoryVoucherEntries");
        return inventoryVoucherEntryService.findAll();
    }

    /**
     * {@code GET  /inventory-voucher-entries/:id} : get the "id" inventoryVoucherEntry.
     *
     * @param id the id of the inventoryVoucherEntryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventoryVoucherEntryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inventory-voucher-entries/{id}")
    public ResponseEntity<InventoryVoucherEntryDTO> getInventoryVoucherEntry(@PathVariable Long id) {
        log.debug("REST request to get InventoryVoucherEntry : {}", id);
        Optional<InventoryVoucherEntryDTO> inventoryVoucherEntryDTO = inventoryVoucherEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inventoryVoucherEntryDTO);
    }

    /**
     * {@code DELETE  /inventory-voucher-entries/:id} : delete the "id" inventoryVoucherEntry.
     *
     * @param id the id of the inventoryVoucherEntryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inventory-voucher-entries/{id}")
    public ResponseEntity<Void> deleteInventoryVoucherEntry(@PathVariable Long id) {
        log.debug("REST request to delete InventoryVoucherEntry : {}", id);
        inventoryVoucherEntryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

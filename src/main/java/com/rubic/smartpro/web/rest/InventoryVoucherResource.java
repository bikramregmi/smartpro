package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.InventoryVoucherService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.InventoryVoucherDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rubic.smartpro.domain.InventoryVoucher}.
 */
@RestController
@RequestMapping("/api")
public class InventoryVoucherResource {

    private final Logger log = LoggerFactory.getLogger(InventoryVoucherResource.class);

    private static final String ENTITY_NAME = "inventoryVoucher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventoryVoucherService inventoryVoucherService;

    public InventoryVoucherResource(InventoryVoucherService inventoryVoucherService) {
        this.inventoryVoucherService = inventoryVoucherService;
    }

    /**
     * {@code POST  /inventory-vouchers} : Create a new inventoryVoucher.
     *
     * @param inventoryVoucherDTO the inventoryVoucherDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventoryVoucherDTO, or with status {@code 400 (Bad Request)} if the inventoryVoucher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inventory-vouchers")
    public ResponseEntity<InventoryVoucherDTO> createInventoryVoucher(@RequestBody InventoryVoucherDTO inventoryVoucherDTO) throws URISyntaxException {
        log.debug("REST request to save InventoryVoucher : {}", inventoryVoucherDTO);
        if (inventoryVoucherDTO.getId() != null) {
           return updateInventoryVoucher(inventoryVoucherDTO);
        }
        InventoryVoucherDTO result = inventoryVoucherService.save(inventoryVoucherDTO);
        return ResponseEntity.created(new URI("/api/inventory-vouchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inventory-vouchers} : Updates an existing inventoryVoucher.
     *
     * @param inventoryVoucherDTO the inventoryVoucherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryVoucherDTO,
     * or with status {@code 400 (Bad Request)} if the inventoryVoucherDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventoryVoucherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inventory-vouchers")
    public ResponseEntity<InventoryVoucherDTO> updateInventoryVoucher(@RequestBody InventoryVoucherDTO inventoryVoucherDTO) throws URISyntaxException {
        log.debug("REST request to update InventoryVoucher : {}", inventoryVoucherDTO);
        InventoryVoucherDTO inventoryVoucherDTO1=inventoryVoucherService.findByVoucherNumberId(inventoryVoucherDTO.getVoucherNumber());
        inventoryVoucherDTO1.setNarration(inventoryVoucherDTO.getNarration());
        InventoryVoucherDTO result = inventoryVoucherService.save(inventoryVoucherDTO1);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inventory-vouchers} : get all the inventoryVouchers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventoryVouchers in body.
     */
    @GetMapping("/inventory-vouchers")
    public ResponseEntity<List<InventoryVoucherDTO>> getAllInventoryVouchers(Pageable pageable) {
        log.debug("REST request to get a page of InventoryVouchers");
        Page<InventoryVoucherDTO> page = inventoryVoucherService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inventory-vouchers/:id} : get the "id" inventoryVoucher.
     *
     * @param id the id of the inventoryVoucherDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventoryVoucherDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inventory-vouchers/{id}")
    public ResponseEntity<InventoryVoucherDTO> getInventoryVoucher(@PathVariable Long id) {
        log.debug("REST request to get InventoryVoucher : {}", id);
        Optional<InventoryVoucherDTO> inventoryVoucherDTO = inventoryVoucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inventoryVoucherDTO);
    }

    /**
     * {@code DELETE  /inventory-vouchers/:id} : delete the "id" inventoryVoucher.
     *
     * @param id the id of the inventoryVoucherDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inventory-vouchers/{id}")
    public ResponseEntity<Void> deleteInventoryVoucher(@PathVariable Long id) {
        log.debug("REST request to delete InventoryVoucher : {}", id);
        inventoryVoucherService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

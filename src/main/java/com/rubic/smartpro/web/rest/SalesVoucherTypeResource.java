package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.SalesVoucherTypeService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.SalesVoucherTypeDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.SalesVoucherType}.
 */
@RestController
@RequestMapping("/api")
public class SalesVoucherTypeResource {

    private final Logger log = LoggerFactory.getLogger(SalesVoucherTypeResource.class);

    private static final String ENTITY_NAME = "salesVoucherType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesVoucherTypeService salesVoucherTypeService;

    public SalesVoucherTypeResource(SalesVoucherTypeService salesVoucherTypeService) {
        this.salesVoucherTypeService = salesVoucherTypeService;
    }

    /**
     * {@code POST  /sales-voucher-types} : Create a new salesVoucherType.
     *
     * @param salesVoucherTypeDTO the salesVoucherTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesVoucherTypeDTO, or with status {@code 400 (Bad Request)} if the salesVoucherType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-voucher-types")
    public ResponseEntity<SalesVoucherTypeDTO> createSalesVoucherType(@RequestBody SalesVoucherTypeDTO salesVoucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to save SalesVoucherType : {}", salesVoucherTypeDTO);
        if (salesVoucherTypeDTO.getId() != null) {
            return updateSalesVoucherType(salesVoucherTypeDTO);
        }
        SalesVoucherTypeDTO result = salesVoucherTypeService.save(salesVoucherTypeDTO);
        return ResponseEntity.created(new URI("/api/sales-voucher-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-voucher-types} : Updates an existing salesVoucherType.
     *
     * @param salesVoucherTypeDTO the salesVoucherTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesVoucherTypeDTO,
     * or with status {@code 400 (Bad Request)} if the salesVoucherTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesVoucherTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-voucher-types")
    public ResponseEntity<SalesVoucherTypeDTO> updateSalesVoucherType(@RequestBody SalesVoucherTypeDTO salesVoucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to update SalesVoucherType : {}", salesVoucherTypeDTO);
        if (salesVoucherTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        salesVoucherTypeDTO.setId(salesVoucherTypeService.findUniqueSalesVoucher(salesVoucherTypeDTO).getId());;
        SalesVoucherTypeDTO result = salesVoucherTypeService.save(salesVoucherTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesVoucherTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sales-voucher-types} : get all the salesVoucherTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesVoucherTypes in body.
     */
    @GetMapping("/sales-voucher-types")
    public ResponseEntity<List<SalesVoucherTypeDTO>> getAllSalesVoucherTypes(Pageable pageable) {
        log.debug("REST request to get a page of SalesVoucherTypes");
        Page<SalesVoucherTypeDTO> page = salesVoucherTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-voucher-types/:id} : get the "id" salesVoucherType.
     *
     * @param id the id of the salesVoucherTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesVoucherTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-voucher-types/{id}")
    public ResponseEntity<SalesVoucherTypeDTO> getSalesVoucherType(@PathVariable Long id) {
        log.debug("REST request to get SalesVoucherType : {}", id);
        Optional<SalesVoucherTypeDTO> salesVoucherTypeDTO = salesVoucherTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesVoucherTypeDTO);
    }

    /**
     * {@code DELETE  /sales-voucher-types/:id} : delete the "id" salesVoucherType.
     *
     * @param id the id of the salesVoucherTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-voucher-types/{id}")
    public ResponseEntity<Void> deleteSalesVoucherType(@PathVariable Long id) {
        log.debug("REST request to delete SalesVoucherType : {}", id);
        salesVoucherTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

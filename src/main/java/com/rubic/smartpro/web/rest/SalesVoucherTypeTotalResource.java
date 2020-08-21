package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.SalesVoucherTypeTotalService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.SalesVoucherTypeTotalDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.SalesVoucherTypeTotal}.
 */
@RestController
@RequestMapping("/api")
public class SalesVoucherTypeTotalResource {

    private final Logger log = LoggerFactory.getLogger(SalesVoucherTypeTotalResource.class);

    private static final String ENTITY_NAME = "salesVoucherTypeTotal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesVoucherTypeTotalService salesVoucherTypeTotalService;

    public SalesVoucherTypeTotalResource(SalesVoucherTypeTotalService salesVoucherTypeTotalService) {
        this.salesVoucherTypeTotalService = salesVoucherTypeTotalService;
    }

    /**
     * {@code POST  /sales-voucher-type-totals} : Create a new salesVoucherTypeTotal.
     *
     * @param salesVoucherTypeTotalDTO the salesVoucherTypeTotalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesVoucherTypeTotalDTO, or with status {@code 400 (Bad Request)} if the salesVoucherTypeTotal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-voucher-type-totals")
    public ResponseEntity<SalesVoucherTypeTotalDTO> createSalesVoucherTypeTotal(@RequestBody SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO) throws URISyntaxException {
        log.debug("REST request to save SalesVoucherTypeTotal : {}", salesVoucherTypeTotalDTO);
        if (salesVoucherTypeTotalDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesVoucherTypeTotal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesVoucherTypeTotalDTO result = salesVoucherTypeTotalService.save(salesVoucherTypeTotalDTO);
        return ResponseEntity.created(new URI("/api/sales-voucher-type-totals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-voucher-type-totals} : Updates an existing salesVoucherTypeTotal.
     *
     * @param salesVoucherTypeTotalDTO the salesVoucherTypeTotalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesVoucherTypeTotalDTO,
     * or with status {@code 400 (Bad Request)} if the salesVoucherTypeTotalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesVoucherTypeTotalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-voucher-type-totals")
    public ResponseEntity<SalesVoucherTypeTotalDTO> updateSalesVoucherTypeTotal(@RequestBody SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO) throws URISyntaxException {
        log.debug("REST request to update SalesVoucherTypeTotal : {}", salesVoucherTypeTotalDTO);
        if (salesVoucherTypeTotalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalesVoucherTypeTotalDTO result = salesVoucherTypeTotalService.save(salesVoucherTypeTotalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesVoucherTypeTotalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sales-voucher-type-totals} : get all the salesVoucherTypeTotals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesVoucherTypeTotals in body.
     */
    @GetMapping("/sales-voucher-type-totals")
    public ResponseEntity<List<SalesVoucherTypeTotalDTO>> getAllSalesVoucherTypeTotals(Pageable pageable) {
        log.debug("REST request to get a page of SalesVoucherTypeTotals");
        Page<SalesVoucherTypeTotalDTO> page = salesVoucherTypeTotalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-voucher-type-totals/:id} : get the "id" salesVoucherTypeTotal.
     *
     * @param id the id of the salesVoucherTypeTotalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesVoucherTypeTotalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-voucher-type-totals/{id}")
    public ResponseEntity<SalesVoucherTypeTotalDTO> getSalesVoucherTypeTotal(@PathVariable Long id) {
        log.debug("REST request to get SalesVoucherTypeTotal : {}", id);
        Optional<SalesVoucherTypeTotalDTO> salesVoucherTypeTotalDTO = salesVoucherTypeTotalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesVoucherTypeTotalDTO);
    }

    /**
     * {@code DELETE  /sales-voucher-type-totals/:id} : delete the "id" salesVoucherTypeTotal.
     *
     * @param id the id of the salesVoucherTypeTotalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-voucher-type-totals/{id}")
    public ResponseEntity<Void> deleteSalesVoucherTypeTotal(@PathVariable Long id) {
        log.debug("REST request to delete SalesVoucherTypeTotal : {}", id);
        salesVoucherTypeTotalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

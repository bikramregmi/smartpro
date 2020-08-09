package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.LedgerService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.LedgerDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rubic.smartpro.domain.Ledger}.
 */
@RestController
@RequestMapping("/api")
public class LedgerResource {

    private final Logger log = LoggerFactory.getLogger(LedgerResource.class);

    private static final String ENTITY_NAME = "ledger";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LedgerService ledgerService;

    public LedgerResource(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    /**
     * {@code POST  /ledgers} : Create a new ledger.
     *
     * @param ledgerDTO the ledgerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ledgerDTO, or with status {@code 400 (Bad Request)} if the ledger has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ledgers")
    public ResponseEntity<LedgerDTO> createLedger(@Valid @RequestBody LedgerDTO ledgerDTO) throws URISyntaxException {
        log.debug("REST request to save Ledger : {}", ledgerDTO);
        if (ledgerDTO.getId() != null) {
            throw new BadRequestAlertException("A new ledger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LedgerDTO result = ledgerService.save(ledgerDTO);
        return ResponseEntity.created(new URI("/api/ledgers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ledgers} : Updates an existing ledger.
     *
     * @param ledgerDTO the ledgerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ledgerDTO,
     * or with status {@code 400 (Bad Request)} if the ledgerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ledgerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ledgers")
    public ResponseEntity<LedgerDTO> updateLedger(@Valid @RequestBody LedgerDTO ledgerDTO) throws URISyntaxException {
        log.debug("REST request to update Ledger : {}", ledgerDTO);
        if (ledgerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LedgerDTO result = ledgerService.save(ledgerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ledgerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ledgers} : get all the ledgers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ledgers in body.
     */
    @GetMapping("/ledgers")
    public ResponseEntity<List<LedgerDTO>> getAllLedgers(Pageable pageable) {
        log.debug("REST request to get a page of Ledgers");
        Page<LedgerDTO> page = ledgerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ledgers/:id} : get the "id" ledger.
     *
     * @param id the id of the ledgerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ledgerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ledgers/{id}")
    public ResponseEntity<LedgerDTO> getLedger(@PathVariable Long id) {
        log.debug("REST request to get Ledger : {}", id);
        Optional<LedgerDTO> ledgerDTO = ledgerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ledgerDTO);
    }

    /**
     * {@code DELETE  /ledgers/:id} : delete the "id" ledger.
     *
     * @param id the id of the ledgerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ledgers/{id}")
    public ResponseEntity<Void> deleteLedger(@PathVariable Long id) {
        log.debug("REST request to delete Ledger : {}", id);
        ledgerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.PayrollGenerateService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.PayrollGenerateDTO;
import com.rubic.smartpro.service.dto.PayrollGenerateCriteria;
import com.rubic.smartpro.service.PayrollGenerateQueryService;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.PayrollGenerate}.
 */
@RestController
@RequestMapping("/api")
public class PayrollGenerateResource {

    private final Logger log = LoggerFactory.getLogger(PayrollGenerateResource.class);

    private static final String ENTITY_NAME = "payrollGenerate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayrollGenerateService payrollGenerateService;

    private final PayrollGenerateQueryService payrollGenerateQueryService;

    public PayrollGenerateResource(PayrollGenerateService payrollGenerateService, PayrollGenerateQueryService payrollGenerateQueryService) {
        this.payrollGenerateService = payrollGenerateService;
        this.payrollGenerateQueryService = payrollGenerateQueryService;
    }

    /**
     * {@code POST  /payroll-generates} : Create a new payrollGenerate.
     *
     * @param payrollGenerateDTO the payrollGenerateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payrollGenerateDTO, or with status {@code 400 (Bad Request)} if the payrollGenerate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payroll-generates")
    public ResponseEntity<PayrollGenerateDTO> createPayrollGenerate(@Valid @RequestBody PayrollGenerateDTO payrollGenerateDTO) throws URISyntaxException {
        log.debug("REST request to save PayrollGenerate : {}", payrollGenerateDTO);
        if (payrollGenerateDTO.getId() != null) {
            throw new BadRequestAlertException("A new payrollGenerate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayrollGenerateDTO result = payrollGenerateService.save(payrollGenerateDTO);
        return ResponseEntity.created(new URI("/api/payroll-generates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payroll-generates} : Updates an existing payrollGenerate.
     *
     * @param payrollGenerateDTO the payrollGenerateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payrollGenerateDTO,
     * or with status {@code 400 (Bad Request)} if the payrollGenerateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payrollGenerateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payroll-generates")
    public ResponseEntity<PayrollGenerateDTO> updatePayrollGenerate(@Valid @RequestBody PayrollGenerateDTO payrollGenerateDTO) throws URISyntaxException {
        log.debug("REST request to update PayrollGenerate : {}", payrollGenerateDTO);
        if (payrollGenerateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayrollGenerateDTO result = payrollGenerateService.save(payrollGenerateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payrollGenerateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payroll-generates} : get all the payrollGenerates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payrollGenerates in body.
     */
    @GetMapping("/payroll-generates")
    public ResponseEntity<List<PayrollGenerateDTO>> getAllPayrollGenerates(PayrollGenerateCriteria criteria, Pageable pageable) {

        log.debug("REST request to get PayrollGenerates by criteria: {}", criteria);
        Page<PayrollGenerateDTO> page = payrollGenerateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payroll-generates/count} : count all the payrollGenerates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/payroll-generates/count")
    public ResponseEntity<Long> countPayrollGenerates(PayrollGenerateCriteria criteria) {
        log.debug("REST request to count PayrollGenerates by criteria: {}", criteria);
        return ResponseEntity.ok().body(payrollGenerateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /payroll-generates/:id} : get the "id" payrollGenerate.
     *
     * @param id the id of the payrollGenerateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payrollGenerateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payroll-generates/{id}")
    public ResponseEntity<PayrollGenerateDTO> getPayrollGenerate(@PathVariable Long id) {
        log.debug("REST request to get PayrollGenerate : {}", id);
        Optional<PayrollGenerateDTO> payrollGenerateDTO = payrollGenerateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payrollGenerateDTO);
    }

    /**
     * {@code DELETE  /payroll-generates/:id} : delete the "id" payrollGenerate.
     *
     * @param id the id of the payrollGenerateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payroll-generates/{id}")
    public ResponseEntity<Void> deletePayrollGenerate(@PathVariable Long id) {
        log.debug("REST request to delete PayrollGenerate : {}", id);
        payrollGenerateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.EmployeeSalaryService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.EmployeeSalaryDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.EmployeeSalary}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeSalaryResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeSalaryResource.class);

    private static final String ENTITY_NAME = "employeeSalary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeSalaryService employeeSalaryService;

    public EmployeeSalaryResource(EmployeeSalaryService employeeSalaryService) {
        this.employeeSalaryService = employeeSalaryService;
    }

    /**
     * {@code POST  /employee-salaries} : Create a new employeeSalary.
     *
     * @param employeeSalaryDTO the employeeSalaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeSalaryDTO, or with status {@code 400 (Bad Request)} if the employeeSalary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employee-salaries")
    public ResponseEntity<EmployeeSalaryDTO> createEmployeeSalary(@RequestBody EmployeeSalaryDTO employeeSalaryDTO) throws URISyntaxException {
        log.debug("REST request to save EmployeeSalary : {}", employeeSalaryDTO);
        if (employeeSalaryDTO.getId() != null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeSalaryDTO result = employeeSalaryService.save(employeeSalaryDTO);
        return ResponseEntity.created(new URI("/api/employee-salaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employee-salaries} : Updates an existing employeeSalary.
     *
     * @param employeeSalaryDTO the employeeSalaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeSalaryDTO,
     * or with status {@code 400 (Bad Request)} if the employeeSalaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeSalaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employee-salaries")
    public ResponseEntity<EmployeeSalaryDTO> updateEmployeeSalary(@RequestBody EmployeeSalaryDTO employeeSalaryDTO) throws URISyntaxException {
        log.debug("REST request to update EmployeeSalary : {}", employeeSalaryDTO);
        if (employeeSalaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeSalaryDTO result = employeeSalaryService.save(employeeSalaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeSalaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /employee-salaries} : get all the employeeSalaries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeeSalaries in body.
     */
    @GetMapping("/employee-salaries")
    public ResponseEntity<List<EmployeeSalaryDTO>> getAllEmployeeSalaries(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeSalaries");
        Page<EmployeeSalaryDTO> page = employeeSalaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employee-salaries/:id} : get the "id" employeeSalary.
     *
     * @param id the id of the employeeSalaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeSalaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employee-salaries/{id}")
    public ResponseEntity<EmployeeSalaryDTO> getEmployeeSalary(@PathVariable Long id) {
        log.debug("REST request to get EmployeeSalary : {}", id);
        Optional<EmployeeSalaryDTO> employeeSalaryDTO = employeeSalaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeSalaryDTO);
    }

    /**
     * {@code DELETE  /employee-salaries/:id} : delete the "id" employeeSalary.
     *
     * @param id the id of the employeeSalaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employee-salaries/{id}")
    public ResponseEntity<Void> deleteEmployeeSalary(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeSalary : {}", id);
        employeeSalaryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

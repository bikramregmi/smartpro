package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.EmployeeInformationService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.EmployeeInformationDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.EmployeeInformation}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeInformationResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeInformationResource.class);

    private static final String ENTITY_NAME = "employeeInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeInformationService employeeInformationService;

    public EmployeeInformationResource(EmployeeInformationService employeeInformationService) {
        this.employeeInformationService = employeeInformationService;
    }

    /**
     * {@code POST  /employee-informations} : Create a new employeeInformation.
     *
     * @param employeeInformationDTO the employeeInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeInformationDTO, or with status {@code 400 (Bad Request)} if the employeeInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employee-informations")
    public ResponseEntity<EmployeeInformationDTO> createEmployeeInformation(@RequestBody EmployeeInformationDTO employeeInformationDTO) throws URISyntaxException {
        log.debug("REST request to save EmployeeInformation : {}", employeeInformationDTO);
       if(employeeInformationDTO.getId()!=null)
           updateEmployeeInformation(employeeInformationDTO);
        EmployeeInformationDTO result = employeeInformationService.save(employeeInformationDTO);
        return ResponseEntity.created(new URI("/api/employee-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employee-informations} : Updates an existing employeeInformation.
     *
     * @param employeeInformationDTO the employeeInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeInformationDTO,
     * or with status {@code 400 (Bad Request)} if the employeeInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employee-informations")
    public ResponseEntity<EmployeeInformationDTO> updateEmployeeInformation(@RequestBody EmployeeInformationDTO employeeInformationDTO) throws URISyntaxException {
        log.debug("REST request to update EmployeeInformation : {}", employeeInformationDTO);
        if (employeeInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeInformationDTO result = employeeInformationService.save(employeeInformationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeInformationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /employee-informations} : get all the employeeInformations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeeInformations in body.
     */
    @GetMapping("/employee-informations")
    public ResponseEntity<List<EmployeeInformationDTO>> getAllEmployeeInformations(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeInformations");
        Page<EmployeeInformationDTO> page = employeeInformationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employee-informations/:id} : get the "id" employeeInformation.
     *
     * @param id the id of the employeeInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employee-informations/{id}")
    public ResponseEntity<EmployeeInformationDTO> getEmployeeInformation(@PathVariable Long id) {
        log.debug("REST request to get EmployeeInformation : {}", id);
        Optional<EmployeeInformationDTO> employeeInformationDTO = employeeInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeInformationDTO);
    }

    /**
     * {@code DELETE  /employee-informations/:id} : delete the "id" employeeInformation.
     *
     * @param id the id of the employeeInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employee-informations/{id}")
    public ResponseEntity<Void> deleteEmployeeInformation(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeInformation : {}", id);
        employeeInformationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

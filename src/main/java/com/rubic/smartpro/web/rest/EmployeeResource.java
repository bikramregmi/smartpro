package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.enumConstants.ResponseStatus;
import com.rubic.smartpro.responseClass.RestResponseDto;
import com.rubic.smartpro.service.EmployeeService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.EmployeeDTO;
import com.rubic.smartpro.service.dto.EmployeeCriteria;
import com.rubic.smartpro.service.EmployeeQueryService;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "employee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeService employeeService;

    private final EmployeeQueryService employeeQueryService;

    public EmployeeResource(EmployeeService employeeService, EmployeeQueryService employeeQueryService) {
        this.employeeService = employeeService;
        this.employeeQueryService = employeeQueryService;
    }

    /**
     * {@code POST  /employees} : Create a new employee.
     *
     * @param employeeDTO the employeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeDTO, or with status {@code 400 (Bad Request)} if the employee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employees")
    public RestResponseDto createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        RestResponseDto restResponseDto=new RestResponseDto();
        log.debug("REST request to save Employee : {}", employeeDTO);
        if (employeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeDTO result = employeeService.save(employeeDTO);
        if(result!=null) {
            restResponseDto.setDetail(result);
            restResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            restResponseDto.setMessage("New Employee Saved SuccessFully");
        } else {
            restResponseDto.setResponseStatus(ResponseStatus.INTERNAL_SERVER_ERROR);
            restResponseDto.setMessage("Error In Saving New Employee");
        }
        return restResponseDto;
    }

    /**
     * {@code PUT  /employees} : Updates an existing employee.
     *
     * @param employeeDTO the employeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeDTO,
     * or with status {@code 400 (Bad Request)} if the employeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employees")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        log.debug("REST request to update Employee : {}", employeeDTO);
        if (employeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeDTO result = employeeService.save(employeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /employees} : get all the employees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @GetMapping("/employees")
    @CrossOrigin
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(EmployeeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Employees by criteria: {}", criteria);
        Page<EmployeeDTO> page = employeeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employees/count} : count all the employees.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/employees/count")
    public ResponseEntity<Long> countEmployees(EmployeeCriteria criteria) {
        log.debug("REST request to count Employees by criteria: {}", criteria);
        return ResponseEntity.ok().body(employeeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /employees/:id} : get the "id" employee.
     *
     * @param id the id of the employeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);
        Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeDTO);
    }

    /**
     * {@code DELETE  /employees/:id} : delete the "id" employee.
     *
     * @param id the id of the employeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        log.debug("REST request to delete Employee : {}", id);
        employeeService.delete(Long.valueOf(String.valueOf(id)));
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

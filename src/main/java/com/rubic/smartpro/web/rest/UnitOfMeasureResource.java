package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.UnitOfMeasureService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.UnitOfMeasureDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.UnitOfMeasure}.
 */
@RestController
@RequestMapping("/api")
public class UnitOfMeasureResource {

    private final Logger log = LoggerFactory.getLogger(UnitOfMeasureResource.class);

    private static final String ENTITY_NAME = "unitOfMeasure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnitOfMeasureService unitOfMeasureService;

    public UnitOfMeasureResource(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }

    /**
     * {@code POST  /unit-of-measures} : Create a new unitOfMeasure.
     *
     * @param unitOfMeasureDTO the unitOfMeasureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unitOfMeasureDTO, or with status {@code 400 (Bad Request)} if the unitOfMeasure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unit-of-measures")
    public ResponseEntity<UnitOfMeasureDTO> createUnitOfMeasure(@RequestBody UnitOfMeasureDTO unitOfMeasureDTO) throws URISyntaxException {
        log.debug("REST request to save UnitOfMeasure : {}", unitOfMeasureDTO);
        if (unitOfMeasureDTO.getId() != null) {
            throw new BadRequestAlertException("A new unitOfMeasure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitOfMeasureDTO result = unitOfMeasureService.save(unitOfMeasureDTO);
        return ResponseEntity.created(new URI("/api/unit-of-measures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unit-of-measures} : Updates an existing unitOfMeasure.
     *
     * @param unitOfMeasureDTO the unitOfMeasureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitOfMeasureDTO,
     * or with status {@code 400 (Bad Request)} if the unitOfMeasureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unitOfMeasureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unit-of-measures")
    public ResponseEntity<UnitOfMeasureDTO> updateUnitOfMeasure(@RequestBody UnitOfMeasureDTO unitOfMeasureDTO) throws URISyntaxException {
        log.debug("REST request to update UnitOfMeasure : {}", unitOfMeasureDTO);
        if (unitOfMeasureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnitOfMeasureDTO result = unitOfMeasureService.save(unitOfMeasureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitOfMeasureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unit-of-measures} : get all the unitOfMeasures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unitOfMeasures in body.
     */
    @GetMapping("/unit-of-measures")
    public ResponseEntity<List<UnitOfMeasureDTO>> getAllUnitOfMeasures(Pageable pageable) {
        log.debug("REST request to get a page of UnitOfMeasures");
        Page<UnitOfMeasureDTO> page = unitOfMeasureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unit-of-measures/:id} : get the "id" unitOfMeasure.
     *
     * @param id the id of the unitOfMeasureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unitOfMeasureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unit-of-measures/{id}")
    public ResponseEntity<UnitOfMeasureDTO> getUnitOfMeasure(@PathVariable Long id) {
        log.debug("REST request to get UnitOfMeasure : {}", id);
        Optional<UnitOfMeasureDTO> unitOfMeasureDTO = unitOfMeasureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unitOfMeasureDTO);
    }

    /**
     * {@code DELETE  /unit-of-measures/:id} : delete the "id" unitOfMeasure.
     *
     * @param id the id of the unitOfMeasureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unit-of-measures/{id}")
    public ResponseEntity<Void> deleteUnitOfMeasure(@PathVariable Long id) {
        log.debug("REST request to delete UnitOfMeasure : {}", id);
        unitOfMeasureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

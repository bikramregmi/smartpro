package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.VoucherTypeService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.VoucherTypeDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.VoucherType}.
 */
@RestController
@RequestMapping("/api")
public class VoucherTypeResource {

    private final Logger log = LoggerFactory.getLogger(VoucherTypeResource.class);

    private static final String ENTITY_NAME = "voucherType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VoucherTypeService voucherTypeService;

    public VoucherTypeResource(VoucherTypeService voucherTypeService) {
        this.voucherTypeService = voucherTypeService;
    }

    /**
     * {@code POST  /voucher-types} : Create a new voucherType.
     *
     * @param voucherTypeDTO the voucherTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new voucherTypeDTO, or with status {@code 400 (Bad Request)} if the voucherType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/voucher-types")
    public ResponseEntity<VoucherTypeDTO> createVoucherType(@Valid @RequestBody VoucherTypeDTO voucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VoucherType : {}", voucherTypeDTO);
        if (voucherTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new voucherType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VoucherTypeDTO result = voucherTypeService.save(voucherTypeDTO);
        return ResponseEntity.created(new URI("/api/voucher-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /voucher-types} : Updates an existing voucherType.
     *
     * @param voucherTypeDTO the voucherTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated voucherTypeDTO,
     * or with status {@code 400 (Bad Request)} if the voucherTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the voucherTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/voucher-types")
    public ResponseEntity<VoucherTypeDTO> updateVoucherType(@Valid @RequestBody VoucherTypeDTO voucherTypeDTO) throws URISyntaxException {
        log.debug("REST request to update VoucherType : {}", voucherTypeDTO);
        if (voucherTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VoucherTypeDTO result = voucherTypeService.save(voucherTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, voucherTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /voucher-types} : get all the voucherTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of voucherTypes in body.
     */
    @GetMapping("/voucher-types")
    public ResponseEntity<List<VoucherTypeDTO>> getAllVoucherTypes(Pageable pageable) {
        log.debug("REST request to get a page of VoucherTypes");
        Page<VoucherTypeDTO> page = voucherTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /voucher-types/:id} : get the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the voucherTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/voucher-types/{id}")
    public ResponseEntity<VoucherTypeDTO> getVoucherType(@PathVariable Long id) {
        log.debug("REST request to get VoucherType : {}", id);
        Optional<VoucherTypeDTO> voucherTypeDTO = voucherTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(voucherTypeDTO);
    }

    /**
     * {@code DELETE  /voucher-types/:id} : delete the "id" voucherType.
     *
     * @param id the id of the voucherTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/voucher-types/{id}")
    public ResponseEntity<Void> deleteVoucherType(@PathVariable Long id) {
        log.debug("REST request to delete VoucherType : {}", id);
        voucherTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

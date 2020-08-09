package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.service.ProductGroupsService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.ProductGroupsDTO;

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
 * REST controller for managing {@link com.rubic.smartpro.domain.ProductGroups}.
 */
@RestController
@RequestMapping("/api")
public class ProductGroupsResource {

    private final Logger log = LoggerFactory.getLogger(ProductGroupsResource.class);

    private static final String ENTITY_NAME = "productGroups";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductGroupsService productGroupsService;

    public ProductGroupsResource(ProductGroupsService productGroupsService) {
        this.productGroupsService = productGroupsService;
    }

    /**
     * {@code POST  /product-groups} : Create a new productGroups.
     *
     * @param productGroupsDTO the productGroupsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productGroupsDTO, or with status {@code 400 (Bad Request)} if the productGroups has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-groups")
    public ResponseEntity<ProductGroupsDTO> createProductGroups(@Valid @RequestBody ProductGroupsDTO productGroupsDTO) throws URISyntaxException {
        log.debug("REST request to save ProductGroups : {}", productGroupsDTO);
        if (productGroupsDTO.getId() != null) {
            throw new BadRequestAlertException("A new productGroups cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductGroupsDTO result = productGroupsService.save(productGroupsDTO);
        return ResponseEntity.created(new URI("/api/product-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-groups} : Updates an existing productGroups.
     *
     * @param productGroupsDTO the productGroupsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productGroupsDTO,
     * or with status {@code 400 (Bad Request)} if the productGroupsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productGroupsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-groups")
    public ResponseEntity<ProductGroupsDTO> updateProductGroups(@Valid @RequestBody ProductGroupsDTO productGroupsDTO) throws URISyntaxException {
        log.debug("REST request to update ProductGroups : {}", productGroupsDTO);
        if (productGroupsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductGroupsDTO result = productGroupsService.save(productGroupsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productGroupsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-groups} : get all the productGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productGroups in body.
     */
    @GetMapping("/product-groups")
    public ResponseEntity<List<ProductGroupsDTO>> getAllProductGroups(Pageable pageable) {
        log.debug("REST request to get a page of ProductGroups");
        Page<ProductGroupsDTO> page = productGroupsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-groups/:id} : get the "id" productGroups.
     *
     * @param id the id of the productGroupsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productGroupsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-groups/{id}")
    public ResponseEntity<ProductGroupsDTO> getProductGroups(@PathVariable Long id) {
        log.debug("REST request to get ProductGroups : {}", id);
        Optional<ProductGroupsDTO> productGroupsDTO = productGroupsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productGroupsDTO);
    }

    /**
     * {@code DELETE  /product-groups/:id} : delete the "id" productGroups.
     *
     * @param id the id of the productGroupsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-groups/{id}")
    public ResponseEntity<Void> deleteProductGroups(@PathVariable Long id) {
        log.debug("REST request to delete ProductGroups : {}", id);
        productGroupsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

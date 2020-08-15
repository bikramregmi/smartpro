package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.enumConstants.AccountingVoucherType;
import com.rubic.smartpro.service.AccountingVoucherService;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import com.rubic.smartpro.service.dto.AccountingVoucherDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.rubic.smartpro.domain.AccountingVoucher}.
 */
@RestController
@RequestMapping("/api")
public class AccountingVoucherResource {

    private final Logger log = LoggerFactory.getLogger(AccountingVoucherResource.class);

    private static final String ENTITY_NAME = "accountingVoucher";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountingVoucherService accountingVoucherService;

    public AccountingVoucherResource(AccountingVoucherService accountingVoucherService) {
        this.accountingVoucherService = accountingVoucherService;
    }

    /**
     * {@code POST  /accounting-vouchers} : Create a new accountingVoucher.
     *
     * @param accountingVoucherDTO the accountingVoucherDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountingVoucherDTO, or with status {@code 400 (Bad Request)} if the accountingVoucher has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/accounting-vouchers")
    public ResponseEntity<AccountingVoucherDTO> createAccountingVoucher(@Valid @RequestBody AccountingVoucherDTO accountingVoucherDTO) throws URISyntaxException {
        log.debug("REST request to save AccountingVoucher : {}", accountingVoucherDTO);
        if (accountingVoucherDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountingVoucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountingVoucherDTO result = accountingVoucherService.save(accountingVoucherDTO);
        return ResponseEntity.created(new URI("/api/accounting-vouchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /accounting-vouchers} : Updates an existing accountingVoucher.
     *
     * @param accountingVoucherDTO the accountingVoucherDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountingVoucherDTO,
     * or with status {@code 400 (Bad Request)} if the accountingVoucherDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountingVoucherDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/accounting-vouchers")
    public ResponseEntity<AccountingVoucherDTO> updateAccountingVoucher(@Valid @RequestBody AccountingVoucherDTO accountingVoucherDTO) throws URISyntaxException {
        log.debug("REST request to update AccountingVoucher : {}", accountingVoucherDTO);
        if (accountingVoucherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountingVoucherDTO result = accountingVoucherService.save(accountingVoucherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountingVoucherDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /accounting-vouchers} : get all the accountingVouchers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountingVouchers in body.
     */
    @GetMapping("/accounting-vouchers")
    public ResponseEntity<List<AccountingVoucherDTO>> getAllAccountingVouchers(Pageable pageable) {
        log.debug("REST request to get a page of AccountingVouchers");
        Page<AccountingVoucherDTO> page = accountingVoucherService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /accounting-vouchers/:id} : get the "id" accountingVoucher.
     *
     * @param id the id of the accountingVoucherDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountingVoucherDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/accounting-vouchers/{id}")
    public ResponseEntity<AccountingVoucherDTO> getAccountingVoucher(@PathVariable Long id) {
        log.debug("REST request to get AccountingVoucher : {}", id);
        Optional<AccountingVoucherDTO> accountingVoucherDTO = accountingVoucherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountingVoucherDTO);
    }

    /**
     * {@code DELETE  /accounting-vouchers/:id} : delete the "id" accountingVoucher.
     *
     * @param id the id of the accountingVoucherDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/accounting-vouchers/{id}")
    public ResponseEntity<Void> deleteAccountingVoucher(@PathVariable Long id) {
        log.debug("REST request to delete AccountingVoucher : {}", id);
        accountingVoucherService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //Get all Accounting Voucher Type

    @GetMapping("/accounting-vouchers/types")
    public ResponseEntity<AccountingVoucherType[]> getAllAccountingVoucherTypes() {
        log.debug("REST request to get a page of AccountingVouchers");
        return ResponseEntity.ok().body(AccountingVoucherType.values());
    }
}

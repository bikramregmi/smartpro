package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.AccountingVoucher;
import com.rubic.smartpro.repository.AccountingVoucherRepository;
import com.rubic.smartpro.service.AccountingVoucherService;
import com.rubic.smartpro.service.dto.AccountingVoucherDTO;
import com.rubic.smartpro.service.mapper.AccountingVoucherMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AccountingVoucherResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountingVoucherResourceIT {

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_BALANCE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_BALANCE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTICULARS = "AAAAAAAAAA";
    private static final String UPDATED_PARTICULARS = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL = "BBBBBBBBBB";

    private static final Double DEFAULT_GRAND_TOTAL = 1D;
    private static final Double UPDATED_GRAND_TOTAL = 2D;

    @Autowired
    private AccountingVoucherRepository accountingVoucherRepository;

    @Autowired
    private AccountingVoucherMapper accountingVoucherMapper;

    @Autowired
    private AccountingVoucherService accountingVoucherService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountingVoucherMockMvc;

    private AccountingVoucher accountingVoucher;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountingVoucher createEntity(EntityManager em) {
        AccountingVoucher accountingVoucher = new AccountingVoucher()
            .accountName(DEFAULT_ACCOUNT_NAME)
            .currentBalance(DEFAULT_CURRENT_BALANCE)
            .particulars(DEFAULT_PARTICULARS)
            .amount(DEFAULT_AMOUNT)
            .narration(DEFAULT_NARRATION)
            .total(DEFAULT_TOTAL)
            .grandTotal(DEFAULT_GRAND_TOTAL);
        return accountingVoucher;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountingVoucher createUpdatedEntity(EntityManager em) {
        AccountingVoucher accountingVoucher = new AccountingVoucher()
            .accountName(UPDATED_ACCOUNT_NAME)
            .currentBalance(UPDATED_CURRENT_BALANCE)
            .particulars(UPDATED_PARTICULARS)
            .amount(UPDATED_AMOUNT)
            .narration(UPDATED_NARRATION)
            .total(UPDATED_TOTAL)
            .grandTotal(UPDATED_GRAND_TOTAL);
        return accountingVoucher;
    }

    @BeforeEach
    public void initTest() {
        accountingVoucher = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountingVoucher() throws Exception {
        int databaseSizeBeforeCreate = accountingVoucherRepository.findAll().size();
        // Create the AccountingVoucher
        AccountingVoucherDTO accountingVoucherDTO = accountingVoucherMapper.toDto(accountingVoucher);
        restAccountingVoucherMockMvc.perform(post("/api/accounting-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountingVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountingVoucher in the database
        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeCreate + 1);
        AccountingVoucher testAccountingVoucher = accountingVoucherList.get(accountingVoucherList.size() - 1);
        assertThat(testAccountingVoucher.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testAccountingVoucher.getCurrentBalance()).isEqualTo(DEFAULT_CURRENT_BALANCE);
        assertThat(testAccountingVoucher.getParticulars()).isEqualTo(DEFAULT_PARTICULARS);
        assertThat(testAccountingVoucher.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAccountingVoucher.getNarration()).isEqualTo(DEFAULT_NARRATION);
        assertThat(testAccountingVoucher.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testAccountingVoucher.getGrandTotal()).isEqualTo(DEFAULT_GRAND_TOTAL);
    }

    @Test
    @Transactional
    public void createAccountingVoucherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountingVoucherRepository.findAll().size();

        // Create the AccountingVoucher with an existing ID
        accountingVoucher.setId(1L);
        AccountingVoucherDTO accountingVoucherDTO = accountingVoucherMapper.toDto(accountingVoucher);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountingVoucherMockMvc.perform(post("/api/accounting-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountingVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountingVoucher in the database
        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAccountNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountingVoucherRepository.findAll().size();
        // set the field null
        accountingVoucher.setAccountName(null);

        // Create the AccountingVoucher, which fails.
        AccountingVoucherDTO accountingVoucherDTO = accountingVoucherMapper.toDto(accountingVoucher);


        restAccountingVoucherMockMvc.perform(post("/api/accounting-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountingVoucherDTO)))
            .andExpect(status().isBadRequest());

        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccountingVouchers() throws Exception {
        // Initialize the database
        accountingVoucherRepository.saveAndFlush(accountingVoucher);

        // Get all the accountingVoucherList
        restAccountingVoucherMockMvc.perform(get("/api/accounting-vouchers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountingVoucher.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].currentBalance").value(hasItem(DEFAULT_CURRENT_BALANCE)))
            .andExpect(jsonPath("$.[*].particulars").value(hasItem(DEFAULT_PARTICULARS)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].grandTotal").value(hasItem(DEFAULT_GRAND_TOTAL.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getAccountingVoucher() throws Exception {
        // Initialize the database
        accountingVoucherRepository.saveAndFlush(accountingVoucher);

        // Get the accountingVoucher
        restAccountingVoucherMockMvc.perform(get("/api/accounting-vouchers/{id}", accountingVoucher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountingVoucher.getId().intValue()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.currentBalance").value(DEFAULT_CURRENT_BALANCE))
            .andExpect(jsonPath("$.particulars").value(DEFAULT_PARTICULARS))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL))
            .andExpect(jsonPath("$.grandTotal").value(DEFAULT_GRAND_TOTAL.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAccountingVoucher() throws Exception {
        // Get the accountingVoucher
        restAccountingVoucherMockMvc.perform(get("/api/accounting-vouchers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountingVoucher() throws Exception {
        // Initialize the database
        accountingVoucherRepository.saveAndFlush(accountingVoucher);

        int databaseSizeBeforeUpdate = accountingVoucherRepository.findAll().size();

        // Update the accountingVoucher
        AccountingVoucher updatedAccountingVoucher = accountingVoucherRepository.findById(accountingVoucher.getId()).get();
        // Disconnect from session so that the updates on updatedAccountingVoucher are not directly saved in db
        em.detach(updatedAccountingVoucher);
        updatedAccountingVoucher
            .accountName(UPDATED_ACCOUNT_NAME)
            .currentBalance(UPDATED_CURRENT_BALANCE)
            .particulars(UPDATED_PARTICULARS)
            .amount(UPDATED_AMOUNT)
            .narration(UPDATED_NARRATION)
            .total(UPDATED_TOTAL)
            .grandTotal(UPDATED_GRAND_TOTAL);
        AccountingVoucherDTO accountingVoucherDTO = accountingVoucherMapper.toDto(updatedAccountingVoucher);

        restAccountingVoucherMockMvc.perform(put("/api/accounting-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountingVoucherDTO)))
            .andExpect(status().isOk());

        // Validate the AccountingVoucher in the database
        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeUpdate);
        AccountingVoucher testAccountingVoucher = accountingVoucherList.get(accountingVoucherList.size() - 1);
        assertThat(testAccountingVoucher.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountingVoucher.getCurrentBalance()).isEqualTo(UPDATED_CURRENT_BALANCE);
        assertThat(testAccountingVoucher.getParticulars()).isEqualTo(UPDATED_PARTICULARS);
        assertThat(testAccountingVoucher.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAccountingVoucher.getNarration()).isEqualTo(UPDATED_NARRATION);
        assertThat(testAccountingVoucher.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testAccountingVoucher.getGrandTotal()).isEqualTo(UPDATED_GRAND_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountingVoucher() throws Exception {
        int databaseSizeBeforeUpdate = accountingVoucherRepository.findAll().size();

        // Create the AccountingVoucher
        AccountingVoucherDTO accountingVoucherDTO = accountingVoucherMapper.toDto(accountingVoucher);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountingVoucherMockMvc.perform(put("/api/accounting-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountingVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountingVoucher in the database
        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountingVoucher() throws Exception {
        // Initialize the database
        accountingVoucherRepository.saveAndFlush(accountingVoucher);

        int databaseSizeBeforeDelete = accountingVoucherRepository.findAll().size();

        // Delete the accountingVoucher
        restAccountingVoucherMockMvc.perform(delete("/api/accounting-vouchers/{id}", accountingVoucher.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountingVoucher> accountingVoucherList = accountingVoucherRepository.findAll();
        assertThat(accountingVoucherList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.InventoryVoucher;
import com.rubic.smartpro.repository.InventoryVoucherRepository;
import com.rubic.smartpro.service.InventoryVoucherService;
import com.rubic.smartpro.service.dto.InventoryVoucherDTO;
import com.rubic.smartpro.service.mapper.InventoryVoucherMapper;

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
 * Integration tests for the {@link InventoryVoucherResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InventoryVoucherResourceIT {

    private static final String DEFAULT_SR_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_SR_ITEM = "BBBBBBBBBB";

    private static final Long DEFAULT_SR_QUANTITY = 1L;
    private static final Long UPDATED_SR_QUANTITY = 2L;

    private static final Double DEFAULT_SR_RATE = 1D;
    private static final Double UPDATED_SR_RATE = 2D;

    private static final Double DEFAULT_SR_AMOUNT = 1D;
    private static final Double UPDATED_SR_AMOUNT = 2D;

    private static final String DEFAULT_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_DES_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_DES_ITEM = "BBBBBBBBBB";

    private static final Long DEFAULT_DES_QUANTITY = 1L;
    private static final Long UPDATED_DES_QUANTITY = 2L;

    private static final Double DEFAULT_DES_RATE = 1D;
    private static final Double UPDATED_DES_RATE = 2D;

    private static final Double DEFAULT_DES_AMOUNT = 1D;
    private static final Double UPDATED_DES_AMOUNT = 2D;

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private InventoryVoucherRepository inventoryVoucherRepository;

    @Autowired
    private InventoryVoucherMapper inventoryVoucherMapper;

    @Autowired
    private InventoryVoucherService inventoryVoucherService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventoryVoucherMockMvc;

    private InventoryVoucher inventoryVoucher;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventoryVoucher createEntity(EntityManager em) {
        InventoryVoucher inventoryVoucher = new InventoryVoucher()
            .srItem(DEFAULT_SR_ITEM)
            .srQuantity(DEFAULT_SR_QUANTITY)
            .srRate(DEFAULT_SR_RATE)
            .srAmount(DEFAULT_SR_AMOUNT)
            .narration(DEFAULT_NARRATION)
            .desItem(DEFAULT_DES_ITEM)
            .desQuantity(DEFAULT_DES_QUANTITY)
            .desRate(DEFAULT_DES_RATE)
            .desAmount(DEFAULT_DES_AMOUNT)
            .extraField(DEFAULT_EXTRA_FIELD);
        return inventoryVoucher;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventoryVoucher createUpdatedEntity(EntityManager em) {
        InventoryVoucher inventoryVoucher = new InventoryVoucher()
            .srItem(UPDATED_SR_ITEM)
            .srQuantity(UPDATED_SR_QUANTITY)
            .srRate(UPDATED_SR_RATE)
            .srAmount(UPDATED_SR_AMOUNT)
            .narration(UPDATED_NARRATION)
            .desItem(UPDATED_DES_ITEM)
            .desQuantity(UPDATED_DES_QUANTITY)
            .desRate(UPDATED_DES_RATE)
            .desAmount(UPDATED_DES_AMOUNT)
            .extraField(UPDATED_EXTRA_FIELD);
        return inventoryVoucher;
    }

    @BeforeEach
    public void initTest() {
        inventoryVoucher = createEntity(em);
    }

    @Test
    @Transactional
    public void createInventoryVoucher() throws Exception {
        int databaseSizeBeforeCreate = inventoryVoucherRepository.findAll().size();
        // Create the InventoryVoucher
        InventoryVoucherDTO inventoryVoucherDTO = inventoryVoucherMapper.toDto(inventoryVoucher);
        restInventoryVoucherMockMvc.perform(post("/api/inventory-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the InventoryVoucher in the database
        List<InventoryVoucher> inventoryVoucherList = inventoryVoucherRepository.findAll();
        assertThat(inventoryVoucherList).hasSize(databaseSizeBeforeCreate + 1);
        InventoryVoucher testInventoryVoucher = inventoryVoucherList.get(inventoryVoucherList.size() - 1);
        assertThat(testInventoryVoucher.getSrItem()).isEqualTo(DEFAULT_SR_ITEM);
        assertThat(testInventoryVoucher.getSrQuantity()).isEqualTo(DEFAULT_SR_QUANTITY);
        assertThat(testInventoryVoucher.getSrRate()).isEqualTo(DEFAULT_SR_RATE);
        assertThat(testInventoryVoucher.getSrAmount()).isEqualTo(DEFAULT_SR_AMOUNT);
        assertThat(testInventoryVoucher.getNarration()).isEqualTo(DEFAULT_NARRATION);
        assertThat(testInventoryVoucher.getDesItem()).isEqualTo(DEFAULT_DES_ITEM);
        assertThat(testInventoryVoucher.getDesQuantity()).isEqualTo(DEFAULT_DES_QUANTITY);
        assertThat(testInventoryVoucher.getDesRate()).isEqualTo(DEFAULT_DES_RATE);
        assertThat(testInventoryVoucher.getDesAmount()).isEqualTo(DEFAULT_DES_AMOUNT);
        assertThat(testInventoryVoucher.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createInventoryVoucherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inventoryVoucherRepository.findAll().size();

        // Create the InventoryVoucher with an existing ID
        inventoryVoucher.setId(1L);
        InventoryVoucherDTO inventoryVoucherDTO = inventoryVoucherMapper.toDto(inventoryVoucher);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventoryVoucherMockMvc.perform(post("/api/inventory-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InventoryVoucher in the database
        List<InventoryVoucher> inventoryVoucherList = inventoryVoucherRepository.findAll();
        assertThat(inventoryVoucherList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInventoryVouchers() throws Exception {
        // Initialize the database
        inventoryVoucherRepository.saveAndFlush(inventoryVoucher);

        // Get all the inventoryVoucherList
        restInventoryVoucherMockMvc.perform(get("/api/inventory-vouchers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventoryVoucher.getId().intValue())))
            .andExpect(jsonPath("$.[*].srItem").value(hasItem(DEFAULT_SR_ITEM)))
            .andExpect(jsonPath("$.[*].srQuantity").value(hasItem(DEFAULT_SR_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].srRate").value(hasItem(DEFAULT_SR_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].srAmount").value(hasItem(DEFAULT_SR_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].narration").value(hasItem(DEFAULT_NARRATION)))
            .andExpect(jsonPath("$.[*].desItem").value(hasItem(DEFAULT_DES_ITEM)))
            .andExpect(jsonPath("$.[*].desQuantity").value(hasItem(DEFAULT_DES_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].desRate").value(hasItem(DEFAULT_DES_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].desAmount").value(hasItem(DEFAULT_DES_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getInventoryVoucher() throws Exception {
        // Initialize the database
        inventoryVoucherRepository.saveAndFlush(inventoryVoucher);

        // Get the inventoryVoucher
        restInventoryVoucherMockMvc.perform(get("/api/inventory-vouchers/{id}", inventoryVoucher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventoryVoucher.getId().intValue()))
            .andExpect(jsonPath("$.srItem").value(DEFAULT_SR_ITEM))
            .andExpect(jsonPath("$.srQuantity").value(DEFAULT_SR_QUANTITY.intValue()))
            .andExpect(jsonPath("$.srRate").value(DEFAULT_SR_RATE.doubleValue()))
            .andExpect(jsonPath("$.srAmount").value(DEFAULT_SR_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.narration").value(DEFAULT_NARRATION))
            .andExpect(jsonPath("$.desItem").value(DEFAULT_DES_ITEM))
            .andExpect(jsonPath("$.desQuantity").value(DEFAULT_DES_QUANTITY.intValue()))
            .andExpect(jsonPath("$.desRate").value(DEFAULT_DES_RATE.doubleValue()))
            .andExpect(jsonPath("$.desAmount").value(DEFAULT_DES_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingInventoryVoucher() throws Exception {
        // Get the inventoryVoucher
        restInventoryVoucherMockMvc.perform(get("/api/inventory-vouchers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInventoryVoucher() throws Exception {
        // Initialize the database
        inventoryVoucherRepository.saveAndFlush(inventoryVoucher);

        int databaseSizeBeforeUpdate = inventoryVoucherRepository.findAll().size();

        // Update the inventoryVoucher
        InventoryVoucher updatedInventoryVoucher = inventoryVoucherRepository.findById(inventoryVoucher.getId()).get();
        // Disconnect from session so that the updates on updatedInventoryVoucher are not directly saved in db
        em.detach(updatedInventoryVoucher);
        updatedInventoryVoucher
            .srItem(UPDATED_SR_ITEM)
            .srQuantity(UPDATED_SR_QUANTITY)
            .srRate(UPDATED_SR_RATE)
            .srAmount(UPDATED_SR_AMOUNT)
            .narration(UPDATED_NARRATION)
            .desItem(UPDATED_DES_ITEM)
            .desQuantity(UPDATED_DES_QUANTITY)
            .desRate(UPDATED_DES_RATE)
            .desAmount(UPDATED_DES_AMOUNT)
            .extraField(UPDATED_EXTRA_FIELD);
        InventoryVoucherDTO inventoryVoucherDTO = inventoryVoucherMapper.toDto(updatedInventoryVoucher);

        restInventoryVoucherMockMvc.perform(put("/api/inventory-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherDTO)))
            .andExpect(status().isOk());

        // Validate the InventoryVoucher in the database
        List<InventoryVoucher> inventoryVoucherList = inventoryVoucherRepository.findAll();
        assertThat(inventoryVoucherList).hasSize(databaseSizeBeforeUpdate);
        InventoryVoucher testInventoryVoucher = inventoryVoucherList.get(inventoryVoucherList.size() - 1);
        assertThat(testInventoryVoucher.getSrItem()).isEqualTo(UPDATED_SR_ITEM);
        assertThat(testInventoryVoucher.getSrQuantity()).isEqualTo(UPDATED_SR_QUANTITY);
        assertThat(testInventoryVoucher.getSrRate()).isEqualTo(UPDATED_SR_RATE);
        assertThat(testInventoryVoucher.getSrAmount()).isEqualTo(UPDATED_SR_AMOUNT);
        assertThat(testInventoryVoucher.getNarration()).isEqualTo(UPDATED_NARRATION);
        assertThat(testInventoryVoucher.getDesItem()).isEqualTo(UPDATED_DES_ITEM);
        assertThat(testInventoryVoucher.getDesQuantity()).isEqualTo(UPDATED_DES_QUANTITY);
        assertThat(testInventoryVoucher.getDesRate()).isEqualTo(UPDATED_DES_RATE);
        assertThat(testInventoryVoucher.getDesAmount()).isEqualTo(UPDATED_DES_AMOUNT);
        assertThat(testInventoryVoucher.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingInventoryVoucher() throws Exception {
        int databaseSizeBeforeUpdate = inventoryVoucherRepository.findAll().size();

        // Create the InventoryVoucher
        InventoryVoucherDTO inventoryVoucherDTO = inventoryVoucherMapper.toDto(inventoryVoucher);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventoryVoucherMockMvc.perform(put("/api/inventory-vouchers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InventoryVoucher in the database
        List<InventoryVoucher> inventoryVoucherList = inventoryVoucherRepository.findAll();
        assertThat(inventoryVoucherList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInventoryVoucher() throws Exception {
        // Initialize the database
        inventoryVoucherRepository.saveAndFlush(inventoryVoucher);

        int databaseSizeBeforeDelete = inventoryVoucherRepository.findAll().size();

        // Delete the inventoryVoucher
        restInventoryVoucherMockMvc.perform(delete("/api/inventory-vouchers/{id}", inventoryVoucher.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InventoryVoucher> inventoryVoucherList = inventoryVoucherRepository.findAll();
        assertThat(inventoryVoucherList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

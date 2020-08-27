package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.InventoryVoucherEntry;
import com.rubic.smartpro.repository.InventoryVoucherEntryRepository;
import com.rubic.smartpro.service.InventoryVoucherEntryService;
import com.rubic.smartpro.service.dto.InventoryVoucherEntryDTO;
import com.rubic.smartpro.service.mapper.InventoryVoucherEntryMapper;

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
 * Integration tests for the {@link InventoryVoucherEntryResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InventoryVoucherEntryResourceIT {

    private static final String DEFAULT_SR_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_SR_ITEM = "BBBBBBBBBB";

    private static final Long DEFAULT_SR_QUANTITY = 1L;
    private static final Long UPDATED_SR_QUANTITY = 2L;

    private static final Double DEFAULT_SR_RATE = 1D;
    private static final Double UPDATED_SR_RATE = 2D;

    private static final Double DEFAULT_SR_AMOUNT = 1D;
    private static final Double UPDATED_SR_AMOUNT = 2D;

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
    private InventoryVoucherEntryRepository inventoryVoucherEntryRepository;

    @Autowired
    private InventoryVoucherEntryMapper inventoryVoucherEntryMapper;

    @Autowired
    private InventoryVoucherEntryService inventoryVoucherEntryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventoryVoucherEntryMockMvc;

    private InventoryVoucherEntry inventoryVoucherEntry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventoryVoucherEntry createEntity(EntityManager em) {
        InventoryVoucherEntry inventoryVoucherEntry = new InventoryVoucherEntry()
            .srItem(DEFAULT_SR_ITEM)
            .srQuantity(DEFAULT_SR_QUANTITY)
            .srRate(DEFAULT_SR_RATE)
            .srAmount(DEFAULT_SR_AMOUNT)
            .desItem(DEFAULT_DES_ITEM)
            .desQuantity(DEFAULT_DES_QUANTITY)
            .desRate(DEFAULT_DES_RATE)
            .desAmount(DEFAULT_DES_AMOUNT)
            .extraField(DEFAULT_EXTRA_FIELD);
        return inventoryVoucherEntry;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InventoryVoucherEntry createUpdatedEntity(EntityManager em) {
        InventoryVoucherEntry inventoryVoucherEntry = new InventoryVoucherEntry()
            .srItem(UPDATED_SR_ITEM)
            .srQuantity(UPDATED_SR_QUANTITY)
            .srRate(UPDATED_SR_RATE)
            .srAmount(UPDATED_SR_AMOUNT)
            .desItem(UPDATED_DES_ITEM)
            .desQuantity(UPDATED_DES_QUANTITY)
            .desRate(UPDATED_DES_RATE)
            .desAmount(UPDATED_DES_AMOUNT)
            .extraField(UPDATED_EXTRA_FIELD);
        return inventoryVoucherEntry;
    }

    @BeforeEach
    public void initTest() {
        inventoryVoucherEntry = createEntity(em);
    }

    @Test
    @Transactional
    public void createInventoryVoucherEntry() throws Exception {
        int databaseSizeBeforeCreate = inventoryVoucherEntryRepository.findAll().size();
        // Create the InventoryVoucherEntry
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO = inventoryVoucherEntryMapper.toDto(inventoryVoucherEntry);
        restInventoryVoucherEntryMockMvc.perform(post("/api/inventory-voucher-entries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherEntryDTO)))
            .andExpect(status().isCreated());

        // Validate the InventoryVoucherEntry in the database
        List<InventoryVoucherEntry> inventoryVoucherEntryList = inventoryVoucherEntryRepository.findAll();
        assertThat(inventoryVoucherEntryList).hasSize(databaseSizeBeforeCreate + 1);
        InventoryVoucherEntry testInventoryVoucherEntry = inventoryVoucherEntryList.get(inventoryVoucherEntryList.size() - 1);
        assertThat(testInventoryVoucherEntry.getSrItem()).isEqualTo(DEFAULT_SR_ITEM);
        assertThat(testInventoryVoucherEntry.getSrQuantity()).isEqualTo(DEFAULT_SR_QUANTITY);
        assertThat(testInventoryVoucherEntry.getSrRate()).isEqualTo(DEFAULT_SR_RATE);
        assertThat(testInventoryVoucherEntry.getSrAmount()).isEqualTo(DEFAULT_SR_AMOUNT);
        assertThat(testInventoryVoucherEntry.getDesItem()).isEqualTo(DEFAULT_DES_ITEM);
        assertThat(testInventoryVoucherEntry.getDesQuantity()).isEqualTo(DEFAULT_DES_QUANTITY);
        assertThat(testInventoryVoucherEntry.getDesRate()).isEqualTo(DEFAULT_DES_RATE);
        assertThat(testInventoryVoucherEntry.getDesAmount()).isEqualTo(DEFAULT_DES_AMOUNT);
        assertThat(testInventoryVoucherEntry.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createInventoryVoucherEntryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inventoryVoucherEntryRepository.findAll().size();

        // Create the InventoryVoucherEntry with an existing ID
        inventoryVoucherEntry.setId(1L);
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO = inventoryVoucherEntryMapper.toDto(inventoryVoucherEntry);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventoryVoucherEntryMockMvc.perform(post("/api/inventory-voucher-entries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherEntryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InventoryVoucherEntry in the database
        List<InventoryVoucherEntry> inventoryVoucherEntryList = inventoryVoucherEntryRepository.findAll();
        assertThat(inventoryVoucherEntryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInventoryVoucherEntries() throws Exception {
        // Initialize the database
        inventoryVoucherEntryRepository.saveAndFlush(inventoryVoucherEntry);

        // Get all the inventoryVoucherEntryList
        restInventoryVoucherEntryMockMvc.perform(get("/api/inventory-voucher-entries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventoryVoucherEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].srItem").value(hasItem(DEFAULT_SR_ITEM)))
            .andExpect(jsonPath("$.[*].srQuantity").value(hasItem(DEFAULT_SR_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].srRate").value(hasItem(DEFAULT_SR_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].srAmount").value(hasItem(DEFAULT_SR_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].desItem").value(hasItem(DEFAULT_DES_ITEM)))
            .andExpect(jsonPath("$.[*].desQuantity").value(hasItem(DEFAULT_DES_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].desRate").value(hasItem(DEFAULT_DES_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].desAmount").value(hasItem(DEFAULT_DES_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getInventoryVoucherEntry() throws Exception {
        // Initialize the database
        inventoryVoucherEntryRepository.saveAndFlush(inventoryVoucherEntry);

        // Get the inventoryVoucherEntry
        restInventoryVoucherEntryMockMvc.perform(get("/api/inventory-voucher-entries/{id}", inventoryVoucherEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventoryVoucherEntry.getId().intValue()))
            .andExpect(jsonPath("$.srItem").value(DEFAULT_SR_ITEM))
            .andExpect(jsonPath("$.srQuantity").value(DEFAULT_SR_QUANTITY.intValue()))
            .andExpect(jsonPath("$.srRate").value(DEFAULT_SR_RATE.doubleValue()))
            .andExpect(jsonPath("$.srAmount").value(DEFAULT_SR_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.desItem").value(DEFAULT_DES_ITEM))
            .andExpect(jsonPath("$.desQuantity").value(DEFAULT_DES_QUANTITY.intValue()))
            .andExpect(jsonPath("$.desRate").value(DEFAULT_DES_RATE.doubleValue()))
            .andExpect(jsonPath("$.desAmount").value(DEFAULT_DES_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingInventoryVoucherEntry() throws Exception {
        // Get the inventoryVoucherEntry
        restInventoryVoucherEntryMockMvc.perform(get("/api/inventory-voucher-entries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInventoryVoucherEntry() throws Exception {
        // Initialize the database
        inventoryVoucherEntryRepository.saveAndFlush(inventoryVoucherEntry);

        int databaseSizeBeforeUpdate = inventoryVoucherEntryRepository.findAll().size();

        // Update the inventoryVoucherEntry
        InventoryVoucherEntry updatedInventoryVoucherEntry = inventoryVoucherEntryRepository.findById(inventoryVoucherEntry.getId()).get();
        // Disconnect from session so that the updates on updatedInventoryVoucherEntry are not directly saved in db
        em.detach(updatedInventoryVoucherEntry);
        updatedInventoryVoucherEntry
            .srItem(UPDATED_SR_ITEM)
            .srQuantity(UPDATED_SR_QUANTITY)
            .srRate(UPDATED_SR_RATE)
            .srAmount(UPDATED_SR_AMOUNT)
            .desItem(UPDATED_DES_ITEM)
            .desQuantity(UPDATED_DES_QUANTITY)
            .desRate(UPDATED_DES_RATE)
            .desAmount(UPDATED_DES_AMOUNT)
            .extraField(UPDATED_EXTRA_FIELD);
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO = inventoryVoucherEntryMapper.toDto(updatedInventoryVoucherEntry);

        restInventoryVoucherEntryMockMvc.perform(put("/api/inventory-voucher-entries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherEntryDTO)))
            .andExpect(status().isOk());

        // Validate the InventoryVoucherEntry in the database
        List<InventoryVoucherEntry> inventoryVoucherEntryList = inventoryVoucherEntryRepository.findAll();
        assertThat(inventoryVoucherEntryList).hasSize(databaseSizeBeforeUpdate);
        InventoryVoucherEntry testInventoryVoucherEntry = inventoryVoucherEntryList.get(inventoryVoucherEntryList.size() - 1);
        assertThat(testInventoryVoucherEntry.getSrItem()).isEqualTo(UPDATED_SR_ITEM);
        assertThat(testInventoryVoucherEntry.getSrQuantity()).isEqualTo(UPDATED_SR_QUANTITY);
        assertThat(testInventoryVoucherEntry.getSrRate()).isEqualTo(UPDATED_SR_RATE);
        assertThat(testInventoryVoucherEntry.getSrAmount()).isEqualTo(UPDATED_SR_AMOUNT);
        assertThat(testInventoryVoucherEntry.getDesItem()).isEqualTo(UPDATED_DES_ITEM);
        assertThat(testInventoryVoucherEntry.getDesQuantity()).isEqualTo(UPDATED_DES_QUANTITY);
        assertThat(testInventoryVoucherEntry.getDesRate()).isEqualTo(UPDATED_DES_RATE);
        assertThat(testInventoryVoucherEntry.getDesAmount()).isEqualTo(UPDATED_DES_AMOUNT);
        assertThat(testInventoryVoucherEntry.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingInventoryVoucherEntry() throws Exception {
        int databaseSizeBeforeUpdate = inventoryVoucherEntryRepository.findAll().size();

        // Create the InventoryVoucherEntry
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO = inventoryVoucherEntryMapper.toDto(inventoryVoucherEntry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventoryVoucherEntryMockMvc.perform(put("/api/inventory-voucher-entries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(inventoryVoucherEntryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InventoryVoucherEntry in the database
        List<InventoryVoucherEntry> inventoryVoucherEntryList = inventoryVoucherEntryRepository.findAll();
        assertThat(inventoryVoucherEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInventoryVoucherEntry() throws Exception {
        // Initialize the database
        inventoryVoucherEntryRepository.saveAndFlush(inventoryVoucherEntry);

        int databaseSizeBeforeDelete = inventoryVoucherEntryRepository.findAll().size();

        // Delete the inventoryVoucherEntry
        restInventoryVoucherEntryMockMvc.perform(delete("/api/inventory-voucher-entries/{id}", inventoryVoucherEntry.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InventoryVoucherEntry> inventoryVoucherEntryList = inventoryVoucherEntryRepository.findAll();
        assertThat(inventoryVoucherEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

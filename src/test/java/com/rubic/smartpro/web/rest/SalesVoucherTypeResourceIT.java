package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.SalesVoucherType;
import com.rubic.smartpro.repository.SalesVoucherTypeRepository;
import com.rubic.smartpro.service.SalesVoucherTypeService;
import com.rubic.smartpro.service.dto.SalesVoucherTypeDTO;
import com.rubic.smartpro.service.mapper.SalesVoucherTypeMapper;

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
 * Integration tests for the {@link SalesVoucherTypeResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SalesVoucherTypeResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Double DEFAULT_RATE = 1D;
    private static final Double UPDATED_RATE = 2D;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_EXTRA = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA = "BBBBBBBBBB";

    @Autowired
    private SalesVoucherTypeRepository salesVoucherTypeRepository;

    @Autowired
    private SalesVoucherTypeMapper salesVoucherTypeMapper;

    @Autowired
    private SalesVoucherTypeService salesVoucherTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesVoucherTypeMockMvc;

    private SalesVoucherType salesVoucherType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesVoucherType createEntity(EntityManager em) {
        SalesVoucherType salesVoucherType = new SalesVoucherType()
            .item(DEFAULT_ITEM)
            .quantity(DEFAULT_QUANTITY)
            .rate(DEFAULT_RATE)
            .amount(DEFAULT_AMOUNT)
            .extra(DEFAULT_EXTRA);
        return salesVoucherType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesVoucherType createUpdatedEntity(EntityManager em) {
        SalesVoucherType salesVoucherType = new SalesVoucherType()
            .item(UPDATED_ITEM)
            .quantity(UPDATED_QUANTITY)
            .rate(UPDATED_RATE)
            .amount(UPDATED_AMOUNT)
            .extra(UPDATED_EXTRA);
        return salesVoucherType;
    }

    @BeforeEach
    public void initTest() {
        salesVoucherType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesVoucherType() throws Exception {
        int databaseSizeBeforeCreate = salesVoucherTypeRepository.findAll().size();
        // Create the SalesVoucherType
        SalesVoucherTypeDTO salesVoucherTypeDTO = salesVoucherTypeMapper.toDto(salesVoucherType);
        restSalesVoucherTypeMockMvc.perform(post("/api/sales-voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesVoucherType in the database
        List<SalesVoucherType> salesVoucherTypeList = salesVoucherTypeRepository.findAll();
        assertThat(salesVoucherTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SalesVoucherType testSalesVoucherType = salesVoucherTypeList.get(salesVoucherTypeList.size() - 1);
        assertThat(testSalesVoucherType.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testSalesVoucherType.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testSalesVoucherType.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testSalesVoucherType.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSalesVoucherType.getExtra()).isEqualTo(DEFAULT_EXTRA);
    }

    @Test
    @Transactional
    public void createSalesVoucherTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesVoucherTypeRepository.findAll().size();

        // Create the SalesVoucherType with an existing ID
        salesVoucherType.setId(1L);
        SalesVoucherTypeDTO salesVoucherTypeDTO = salesVoucherTypeMapper.toDto(salesVoucherType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesVoucherTypeMockMvc.perform(post("/api/sales-voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesVoucherType in the database
        List<SalesVoucherType> salesVoucherTypeList = salesVoucherTypeRepository.findAll();
        assertThat(salesVoucherTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalesVoucherTypes() throws Exception {
        // Initialize the database
        salesVoucherTypeRepository.saveAndFlush(salesVoucherType);

        // Get all the salesVoucherTypeList
        restSalesVoucherTypeMockMvc.perform(get("/api/sales-voucher-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesVoucherType.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].extra").value(hasItem(DEFAULT_EXTRA)));
    }
    
    @Test
    @Transactional
    public void getSalesVoucherType() throws Exception {
        // Initialize the database
        salesVoucherTypeRepository.saveAndFlush(salesVoucherType);

        // Get the salesVoucherType
        restSalesVoucherTypeMockMvc.perform(get("/api/sales-voucher-types/{id}", salesVoucherType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesVoucherType.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.doubleValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.extra").value(DEFAULT_EXTRA));
    }
    @Test
    @Transactional
    public void getNonExistingSalesVoucherType() throws Exception {
        // Get the salesVoucherType
        restSalesVoucherTypeMockMvc.perform(get("/api/sales-voucher-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesVoucherType() throws Exception {
        // Initialize the database
        salesVoucherTypeRepository.saveAndFlush(salesVoucherType);

        int databaseSizeBeforeUpdate = salesVoucherTypeRepository.findAll().size();

        // Update the salesVoucherType
        SalesVoucherType updatedSalesVoucherType = salesVoucherTypeRepository.findById(salesVoucherType.getId()).get();
        // Disconnect from session so that the updates on updatedSalesVoucherType are not directly saved in db
        em.detach(updatedSalesVoucherType);
        updatedSalesVoucherType
            .item(UPDATED_ITEM)
            .quantity(UPDATED_QUANTITY)
            .rate(UPDATED_RATE)
            .amount(UPDATED_AMOUNT)
            .extra(UPDATED_EXTRA);
        SalesVoucherTypeDTO salesVoucherTypeDTO = salesVoucherTypeMapper.toDto(updatedSalesVoucherType);

        restSalesVoucherTypeMockMvc.perform(put("/api/sales-voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeDTO)))
            .andExpect(status().isOk());

        // Validate the SalesVoucherType in the database
        List<SalesVoucherType> salesVoucherTypeList = salesVoucherTypeRepository.findAll();
        assertThat(salesVoucherTypeList).hasSize(databaseSizeBeforeUpdate);
        SalesVoucherType testSalesVoucherType = salesVoucherTypeList.get(salesVoucherTypeList.size() - 1);
        assertThat(testSalesVoucherType.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSalesVoucherType.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testSalesVoucherType.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testSalesVoucherType.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSalesVoucherType.getExtra()).isEqualTo(UPDATED_EXTRA);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesVoucherType() throws Exception {
        int databaseSizeBeforeUpdate = salesVoucherTypeRepository.findAll().size();

        // Create the SalesVoucherType
        SalesVoucherTypeDTO salesVoucherTypeDTO = salesVoucherTypeMapper.toDto(salesVoucherType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesVoucherTypeMockMvc.perform(put("/api/sales-voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesVoucherType in the database
        List<SalesVoucherType> salesVoucherTypeList = salesVoucherTypeRepository.findAll();
        assertThat(salesVoucherTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesVoucherType() throws Exception {
        // Initialize the database
        salesVoucherTypeRepository.saveAndFlush(salesVoucherType);

        int databaseSizeBeforeDelete = salesVoucherTypeRepository.findAll().size();

        // Delete the salesVoucherType
        restSalesVoucherTypeMockMvc.perform(delete("/api/sales-voucher-types/{id}", salesVoucherType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesVoucherType> salesVoucherTypeList = salesVoucherTypeRepository.findAll();
        assertThat(salesVoucherTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

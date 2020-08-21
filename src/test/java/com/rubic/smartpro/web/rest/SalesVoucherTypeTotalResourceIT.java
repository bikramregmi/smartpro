package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.SalesVoucherTypeTotal;
import com.rubic.smartpro.repository.SalesVoucherTypeTotalRepository;
import com.rubic.smartpro.service.SalesVoucherTypeTotalService;
import com.rubic.smartpro.service.dto.SalesVoucherTypeTotalDTO;
import com.rubic.smartpro.service.mapper.SalesVoucherTypeTotalMapper;

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
 * Integration tests for the {@link SalesVoucherTypeTotalResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SalesVoucherTypeTotalResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITY_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY_TOTAL = "BBBBBBBBBB";

    private static final Double DEFAULT_RATE_TOTAL = 1D;
    private static final Double UPDATED_RATE_TOTAL = 2D;

    private static final Double DEFAULT_AMOUNT_TOTAL = 1D;
    private static final Double UPDATED_AMOUNT_TOTAL = 2D;

    private static final String DEFAULT_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private SalesVoucherTypeTotalRepository salesVoucherTypeTotalRepository;

    @Autowired
    private SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper;

    @Autowired
    private SalesVoucherTypeTotalService salesVoucherTypeTotalService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesVoucherTypeTotalMockMvc;

    private SalesVoucherTypeTotal salesVoucherTypeTotal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesVoucherTypeTotal createEntity(EntityManager em) {
        SalesVoucherTypeTotal salesVoucherTypeTotal = new SalesVoucherTypeTotal()
            .item(DEFAULT_ITEM)
            .quantityTotal(DEFAULT_QUANTITY_TOTAL)
            .rateTotal(DEFAULT_RATE_TOTAL)
            .amountTotal(DEFAULT_AMOUNT_TOTAL)
            .referenceNumber(DEFAULT_REFERENCE_NUMBER)
            .extraField(DEFAULT_EXTRA_FIELD);
        return salesVoucherTypeTotal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesVoucherTypeTotal createUpdatedEntity(EntityManager em) {
        SalesVoucherTypeTotal salesVoucherTypeTotal = new SalesVoucherTypeTotal()
            .item(UPDATED_ITEM)
            .quantityTotal(UPDATED_QUANTITY_TOTAL)
            .rateTotal(UPDATED_RATE_TOTAL)
            .amountTotal(UPDATED_AMOUNT_TOTAL)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .extraField(UPDATED_EXTRA_FIELD);
        return salesVoucherTypeTotal;
    }

    @BeforeEach
    public void initTest() {
        salesVoucherTypeTotal = createEntity(em);
    }

    @Test
    @Transactional
    public void createSalesVoucherTypeTotal() throws Exception {
        int databaseSizeBeforeCreate = salesVoucherTypeTotalRepository.findAll().size();
        // Create the SalesVoucherTypeTotal
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO = salesVoucherTypeTotalMapper.toDto(salesVoucherTypeTotal);
        restSalesVoucherTypeTotalMockMvc.perform(post("/api/sales-voucher-type-totals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeTotalDTO)))
            .andExpect(status().isCreated());

        // Validate the SalesVoucherTypeTotal in the database
        List<SalesVoucherTypeTotal> salesVoucherTypeTotalList = salesVoucherTypeTotalRepository.findAll();
        assertThat(salesVoucherTypeTotalList).hasSize(databaseSizeBeforeCreate + 1);
        SalesVoucherTypeTotal testSalesVoucherTypeTotal = salesVoucherTypeTotalList.get(salesVoucherTypeTotalList.size() - 1);
        assertThat(testSalesVoucherTypeTotal.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testSalesVoucherTypeTotal.getQuantityTotal()).isEqualTo(DEFAULT_QUANTITY_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getRateTotal()).isEqualTo(DEFAULT_RATE_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getAmountTotal()).isEqualTo(DEFAULT_AMOUNT_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getReferenceNumber()).isEqualTo(DEFAULT_REFERENCE_NUMBER);
        assertThat(testSalesVoucherTypeTotal.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createSalesVoucherTypeTotalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salesVoucherTypeTotalRepository.findAll().size();

        // Create the SalesVoucherTypeTotal with an existing ID
        salesVoucherTypeTotal.setId(1L);
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO = salesVoucherTypeTotalMapper.toDto(salesVoucherTypeTotal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesVoucherTypeTotalMockMvc.perform(post("/api/sales-voucher-type-totals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeTotalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesVoucherTypeTotal in the database
        List<SalesVoucherTypeTotal> salesVoucherTypeTotalList = salesVoucherTypeTotalRepository.findAll();
        assertThat(salesVoucherTypeTotalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalesVoucherTypeTotals() throws Exception {
        // Initialize the database
        salesVoucherTypeTotalRepository.saveAndFlush(salesVoucherTypeTotal);

        // Get all the salesVoucherTypeTotalList
        restSalesVoucherTypeTotalMockMvc.perform(get("/api/sales-voucher-type-totals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesVoucherTypeTotal.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].quantityTotal").value(hasItem(DEFAULT_QUANTITY_TOTAL)))
            .andExpect(jsonPath("$.[*].rateTotal").value(hasItem(DEFAULT_RATE_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].amountTotal").value(hasItem(DEFAULT_AMOUNT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].referenceNumber").value(hasItem(DEFAULT_REFERENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getSalesVoucherTypeTotal() throws Exception {
        // Initialize the database
        salesVoucherTypeTotalRepository.saveAndFlush(salesVoucherTypeTotal);

        // Get the salesVoucherTypeTotal
        restSalesVoucherTypeTotalMockMvc.perform(get("/api/sales-voucher-type-totals/{id}", salesVoucherTypeTotal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesVoucherTypeTotal.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.quantityTotal").value(DEFAULT_QUANTITY_TOTAL))
            .andExpect(jsonPath("$.rateTotal").value(DEFAULT_RATE_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.amountTotal").value(DEFAULT_AMOUNT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.referenceNumber").value(DEFAULT_REFERENCE_NUMBER))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingSalesVoucherTypeTotal() throws Exception {
        // Get the salesVoucherTypeTotal
        restSalesVoucherTypeTotalMockMvc.perform(get("/api/sales-voucher-type-totals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSalesVoucherTypeTotal() throws Exception {
        // Initialize the database
        salesVoucherTypeTotalRepository.saveAndFlush(salesVoucherTypeTotal);

        int databaseSizeBeforeUpdate = salesVoucherTypeTotalRepository.findAll().size();

        // Update the salesVoucherTypeTotal
        SalesVoucherTypeTotal updatedSalesVoucherTypeTotal = salesVoucherTypeTotalRepository.findById(salesVoucherTypeTotal.getId()).get();
        // Disconnect from session so that the updates on updatedSalesVoucherTypeTotal are not directly saved in db
        em.detach(updatedSalesVoucherTypeTotal);
        updatedSalesVoucherTypeTotal
            .item(UPDATED_ITEM)
            .quantityTotal(UPDATED_QUANTITY_TOTAL)
            .rateTotal(UPDATED_RATE_TOTAL)
            .amountTotal(UPDATED_AMOUNT_TOTAL)
            .referenceNumber(UPDATED_REFERENCE_NUMBER)
            .extraField(UPDATED_EXTRA_FIELD);
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO = salesVoucherTypeTotalMapper.toDto(updatedSalesVoucherTypeTotal);

        restSalesVoucherTypeTotalMockMvc.perform(put("/api/sales-voucher-type-totals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeTotalDTO)))
            .andExpect(status().isOk());

        // Validate the SalesVoucherTypeTotal in the database
        List<SalesVoucherTypeTotal> salesVoucherTypeTotalList = salesVoucherTypeTotalRepository.findAll();
        assertThat(salesVoucherTypeTotalList).hasSize(databaseSizeBeforeUpdate);
        SalesVoucherTypeTotal testSalesVoucherTypeTotal = salesVoucherTypeTotalList.get(salesVoucherTypeTotalList.size() - 1);
        assertThat(testSalesVoucherTypeTotal.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testSalesVoucherTypeTotal.getQuantityTotal()).isEqualTo(UPDATED_QUANTITY_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getRateTotal()).isEqualTo(UPDATED_RATE_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getAmountTotal()).isEqualTo(UPDATED_AMOUNT_TOTAL);
        assertThat(testSalesVoucherTypeTotal.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
        assertThat(testSalesVoucherTypeTotal.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingSalesVoucherTypeTotal() throws Exception {
        int databaseSizeBeforeUpdate = salesVoucherTypeTotalRepository.findAll().size();

        // Create the SalesVoucherTypeTotal
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO = salesVoucherTypeTotalMapper.toDto(salesVoucherTypeTotal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesVoucherTypeTotalMockMvc.perform(put("/api/sales-voucher-type-totals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salesVoucherTypeTotalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SalesVoucherTypeTotal in the database
        List<SalesVoucherTypeTotal> salesVoucherTypeTotalList = salesVoucherTypeTotalRepository.findAll();
        assertThat(salesVoucherTypeTotalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSalesVoucherTypeTotal() throws Exception {
        // Initialize the database
        salesVoucherTypeTotalRepository.saveAndFlush(salesVoucherTypeTotal);

        int databaseSizeBeforeDelete = salesVoucherTypeTotalRepository.findAll().size();

        // Delete the salesVoucherTypeTotal
        restSalesVoucherTypeTotalMockMvc.perform(delete("/api/sales-voucher-type-totals/{id}", salesVoucherTypeTotal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesVoucherTypeTotal> salesVoucherTypeTotalList = salesVoucherTypeTotalRepository.findAll();
        assertThat(salesVoucherTypeTotalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

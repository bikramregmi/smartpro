package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.Ledger;
import com.rubic.smartpro.repository.LedgerRepository;
import com.rubic.smartpro.service.LedgerService;
import com.rubic.smartpro.service.dto.LedgerDTO;
import com.rubic.smartpro.service.mapper.LedgerMapper;

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
 * Integration tests for the {@link LedgerResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LedgerResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAILING_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MAILING_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAILING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_MAILING_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXTRA_FIELD_1 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_1 = true;

    private static final Boolean DEFAULT_EXTRA_FIELD_2 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_2 = true;

    private static final Boolean DEFAULT_EXTRA_FIELD_3 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_3 = true;

    private static final String DEFAULT_EXTRA_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD_5 = "BBBBBBBBBB";

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private LedgerMapper ledgerMapper;

    @Autowired
    private LedgerService ledgerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLedgerMockMvc;

    private Ledger ledger;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ledger createEntity(EntityManager em) {
        Ledger ledger = new Ledger()
            .name(DEFAULT_NAME)
            .mailingName(DEFAULT_MAILING_NAME)
            .mailingAddress(DEFAULT_MAILING_ADDRESS)
            .panNo(DEFAULT_PAN_NO)
            .extraField1(DEFAULT_EXTRA_FIELD_1)
            .extraField2(DEFAULT_EXTRA_FIELD_2)
            .extraField3(DEFAULT_EXTRA_FIELD_3)
            .extraField4(DEFAULT_EXTRA_FIELD_4)
            .extraField5(DEFAULT_EXTRA_FIELD_5);
        return ledger;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ledger createUpdatedEntity(EntityManager em) {
        Ledger ledger = new Ledger()
            .name(UPDATED_NAME)
            .mailingName(UPDATED_MAILING_NAME)
            .mailingAddress(UPDATED_MAILING_ADDRESS)
            .panNo(UPDATED_PAN_NO)
            .extraField1(UPDATED_EXTRA_FIELD_1)
            .extraField2(UPDATED_EXTRA_FIELD_2)
            .extraField3(UPDATED_EXTRA_FIELD_3)
            .extraField4(UPDATED_EXTRA_FIELD_4)
            .extraField5(UPDATED_EXTRA_FIELD_5);
        return ledger;
    }

    @BeforeEach
    public void initTest() {
        ledger = createEntity(em);
    }

    @Test
    @Transactional
    public void createLedger() throws Exception {
        int databaseSizeBeforeCreate = ledgerRepository.findAll().size();
        // Create the Ledger
        LedgerDTO ledgerDTO = ledgerMapper.toDto(ledger);
        restLedgerMockMvc.perform(post("/api/ledgers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ledgerDTO)))
            .andExpect(status().isCreated());

        // Validate the Ledger in the database
        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeCreate + 1);
        Ledger testLedger = ledgerList.get(ledgerList.size() - 1);
        assertThat(testLedger.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLedger.getMailingName()).isEqualTo(DEFAULT_MAILING_NAME);
        assertThat(testLedger.getMailingAddress()).isEqualTo(DEFAULT_MAILING_ADDRESS);
        assertThat(testLedger.getPanNo()).isEqualTo(DEFAULT_PAN_NO);
        assertThat(testLedger.isExtraField1()).isEqualTo(DEFAULT_EXTRA_FIELD_1);
        assertThat(testLedger.isExtraField2()).isEqualTo(DEFAULT_EXTRA_FIELD_2);
        assertThat(testLedger.isExtraField3()).isEqualTo(DEFAULT_EXTRA_FIELD_3);
        assertThat(testLedger.getExtraField4()).isEqualTo(DEFAULT_EXTRA_FIELD_4);
        assertThat(testLedger.getExtraField5()).isEqualTo(DEFAULT_EXTRA_FIELD_5);
    }

    @Test
    @Transactional
    public void createLedgerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ledgerRepository.findAll().size();

        // Create the Ledger with an existing ID
        ledger.setId(1L);
        LedgerDTO ledgerDTO = ledgerMapper.toDto(ledger);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLedgerMockMvc.perform(post("/api/ledgers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ledgerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ledger in the database
        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ledgerRepository.findAll().size();
        // set the field null
        ledger.setName(null);

        // Create the Ledger, which fails.
        LedgerDTO ledgerDTO = ledgerMapper.toDto(ledger);


        restLedgerMockMvc.perform(post("/api/ledgers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ledgerDTO)))
            .andExpect(status().isBadRequest());

        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLedgers() throws Exception {
        // Initialize the database
        ledgerRepository.saveAndFlush(ledger);

        // Get all the ledgerList
        restLedgerMockMvc.perform(get("/api/ledgers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ledger.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mailingName").value(hasItem(DEFAULT_MAILING_NAME)))
            .andExpect(jsonPath("$.[*].mailingAddress").value(hasItem(DEFAULT_MAILING_ADDRESS)))
            .andExpect(jsonPath("$.[*].panNo").value(hasItem(DEFAULT_PAN_NO)))
            .andExpect(jsonPath("$.[*].extraField1").value(hasItem(DEFAULT_EXTRA_FIELD_1.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField2").value(hasItem(DEFAULT_EXTRA_FIELD_2.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField3").value(hasItem(DEFAULT_EXTRA_FIELD_3.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField4").value(hasItem(DEFAULT_EXTRA_FIELD_4)))
            .andExpect(jsonPath("$.[*].extraField5").value(hasItem(DEFAULT_EXTRA_FIELD_5)));
    }
    
    @Test
    @Transactional
    public void getLedger() throws Exception {
        // Initialize the database
        ledgerRepository.saveAndFlush(ledger);

        // Get the ledger
        restLedgerMockMvc.perform(get("/api/ledgers/{id}", ledger.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ledger.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mailingName").value(DEFAULT_MAILING_NAME))
            .andExpect(jsonPath("$.mailingAddress").value(DEFAULT_MAILING_ADDRESS))
            .andExpect(jsonPath("$.panNo").value(DEFAULT_PAN_NO))
            .andExpect(jsonPath("$.extraField1").value(DEFAULT_EXTRA_FIELD_1.booleanValue()))
            .andExpect(jsonPath("$.extraField2").value(DEFAULT_EXTRA_FIELD_2.booleanValue()))
            .andExpect(jsonPath("$.extraField3").value(DEFAULT_EXTRA_FIELD_3.booleanValue()))
            .andExpect(jsonPath("$.extraField4").value(DEFAULT_EXTRA_FIELD_4))
            .andExpect(jsonPath("$.extraField5").value(DEFAULT_EXTRA_FIELD_5));
    }
    @Test
    @Transactional
    public void getNonExistingLedger() throws Exception {
        // Get the ledger
        restLedgerMockMvc.perform(get("/api/ledgers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLedger() throws Exception {
        // Initialize the database
        ledgerRepository.saveAndFlush(ledger);

        int databaseSizeBeforeUpdate = ledgerRepository.findAll().size();

        // Update the ledger
        Ledger updatedLedger = ledgerRepository.findById(ledger.getId()).get();
        // Disconnect from session so that the updates on updatedLedger are not directly saved in db
        em.detach(updatedLedger);
        updatedLedger
            .name(UPDATED_NAME)
            .mailingName(UPDATED_MAILING_NAME)
            .mailingAddress(UPDATED_MAILING_ADDRESS)
            .panNo(UPDATED_PAN_NO)
            .extraField1(UPDATED_EXTRA_FIELD_1)
            .extraField2(UPDATED_EXTRA_FIELD_2)
            .extraField3(UPDATED_EXTRA_FIELD_3)
            .extraField4(UPDATED_EXTRA_FIELD_4)
            .extraField5(UPDATED_EXTRA_FIELD_5);
        LedgerDTO ledgerDTO = ledgerMapper.toDto(updatedLedger);

        restLedgerMockMvc.perform(put("/api/ledgers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ledgerDTO)))
            .andExpect(status().isOk());

        // Validate the Ledger in the database
        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeUpdate);
        Ledger testLedger = ledgerList.get(ledgerList.size() - 1);
        assertThat(testLedger.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLedger.getMailingName()).isEqualTo(UPDATED_MAILING_NAME);
        assertThat(testLedger.getMailingAddress()).isEqualTo(UPDATED_MAILING_ADDRESS);
        assertThat(testLedger.getPanNo()).isEqualTo(UPDATED_PAN_NO);
        assertThat(testLedger.isExtraField1()).isEqualTo(UPDATED_EXTRA_FIELD_1);
        assertThat(testLedger.isExtraField2()).isEqualTo(UPDATED_EXTRA_FIELD_2);
        assertThat(testLedger.isExtraField3()).isEqualTo(UPDATED_EXTRA_FIELD_3);
        assertThat(testLedger.getExtraField4()).isEqualTo(UPDATED_EXTRA_FIELD_4);
        assertThat(testLedger.getExtraField5()).isEqualTo(UPDATED_EXTRA_FIELD_5);
    }

    @Test
    @Transactional
    public void updateNonExistingLedger() throws Exception {
        int databaseSizeBeforeUpdate = ledgerRepository.findAll().size();

        // Create the Ledger
        LedgerDTO ledgerDTO = ledgerMapper.toDto(ledger);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLedgerMockMvc.perform(put("/api/ledgers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ledgerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ledger in the database
        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLedger() throws Exception {
        // Initialize the database
        ledgerRepository.saveAndFlush(ledger);

        int databaseSizeBeforeDelete = ledgerRepository.findAll().size();

        // Delete the ledger
        restLedgerMockMvc.perform(delete("/api/ledgers/{id}", ledger.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ledger> ledgerList = ledgerRepository.findAll();
        assertThat(ledgerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

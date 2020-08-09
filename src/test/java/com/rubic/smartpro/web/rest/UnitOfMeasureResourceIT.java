package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.UnitOfMeasure;
import com.rubic.smartpro.repository.UnitOfMeasureRepository;
import com.rubic.smartpro.service.UnitOfMeasureService;
import com.rubic.smartpro.service.dto.UnitOfMeasureDTO;
import com.rubic.smartpro.service.mapper.UnitOfMeasureMapper;

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
 * Integration tests for the {@link UnitOfMeasureResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UnitOfMeasureResourceIT {

    private static final String DEFAULT_FORMAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FORMAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL = "BBBBBBBBBB";

    private static final String DEFAULT_DECIMAL_PLACES = "AAAAAAAAAA";
    private static final String UPDATED_DECIMAL_PLACES = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private UnitOfMeasureMapper unitOfMeasureMapper;

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitOfMeasureMockMvc;

    private UnitOfMeasure unitOfMeasure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitOfMeasure createEntity(EntityManager em) {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure()
            .formalName(DEFAULT_FORMAL_NAME)
            .type(DEFAULT_TYPE)
            .symbol(DEFAULT_SYMBOL)
            .decimalPlaces(DEFAULT_DECIMAL_PLACES)
            .extraField(DEFAULT_EXTRA_FIELD);
        return unitOfMeasure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitOfMeasure createUpdatedEntity(EntityManager em) {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure()
            .formalName(UPDATED_FORMAL_NAME)
            .type(UPDATED_TYPE)
            .symbol(UPDATED_SYMBOL)
            .decimalPlaces(UPDATED_DECIMAL_PLACES)
            .extraField(UPDATED_EXTRA_FIELD);
        return unitOfMeasure;
    }

    @BeforeEach
    public void initTest() {
        unitOfMeasure = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitOfMeasure() throws Exception {
        int databaseSizeBeforeCreate = unitOfMeasureRepository.findAll().size();
        // Create the UnitOfMeasure
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureMapper.toDto(unitOfMeasure);
        restUnitOfMeasureMockMvc.perform(post("/api/unit-of-measures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitOfMeasureDTO)))
            .andExpect(status().isCreated());

        // Validate the UnitOfMeasure in the database
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureRepository.findAll();
        assertThat(unitOfMeasureList).hasSize(databaseSizeBeforeCreate + 1);
        UnitOfMeasure testUnitOfMeasure = unitOfMeasureList.get(unitOfMeasureList.size() - 1);
        assertThat(testUnitOfMeasure.getFormalName()).isEqualTo(DEFAULT_FORMAL_NAME);
        assertThat(testUnitOfMeasure.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testUnitOfMeasure.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
        assertThat(testUnitOfMeasure.getDecimalPlaces()).isEqualTo(DEFAULT_DECIMAL_PLACES);
        assertThat(testUnitOfMeasure.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createUnitOfMeasureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitOfMeasureRepository.findAll().size();

        // Create the UnitOfMeasure with an existing ID
        unitOfMeasure.setId(1L);
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureMapper.toDto(unitOfMeasure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitOfMeasureMockMvc.perform(post("/api/unit-of-measures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitOfMeasureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnitOfMeasure in the database
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureRepository.findAll();
        assertThat(unitOfMeasureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUnitOfMeasures() throws Exception {
        // Initialize the database
        unitOfMeasureRepository.saveAndFlush(unitOfMeasure);

        // Get all the unitOfMeasureList
        restUnitOfMeasureMockMvc.perform(get("/api/unit-of-measures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitOfMeasure.getId().intValue())))
            .andExpect(jsonPath("$.[*].formalName").value(hasItem(DEFAULT_FORMAL_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL)))
            .andExpect(jsonPath("$.[*].decimalPlaces").value(hasItem(DEFAULT_DECIMAL_PLACES)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getUnitOfMeasure() throws Exception {
        // Initialize the database
        unitOfMeasureRepository.saveAndFlush(unitOfMeasure);

        // Get the unitOfMeasure
        restUnitOfMeasureMockMvc.perform(get("/api/unit-of-measures/{id}", unitOfMeasure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unitOfMeasure.getId().intValue()))
            .andExpect(jsonPath("$.formalName").value(DEFAULT_FORMAL_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL))
            .andExpect(jsonPath("$.decimalPlaces").value(DEFAULT_DECIMAL_PLACES))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingUnitOfMeasure() throws Exception {
        // Get the unitOfMeasure
        restUnitOfMeasureMockMvc.perform(get("/api/unit-of-measures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitOfMeasure() throws Exception {
        // Initialize the database
        unitOfMeasureRepository.saveAndFlush(unitOfMeasure);

        int databaseSizeBeforeUpdate = unitOfMeasureRepository.findAll().size();

        // Update the unitOfMeasure
        UnitOfMeasure updatedUnitOfMeasure = unitOfMeasureRepository.findById(unitOfMeasure.getId()).get();
        // Disconnect from session so that the updates on updatedUnitOfMeasure are not directly saved in db
        em.detach(updatedUnitOfMeasure);
        updatedUnitOfMeasure
            .formalName(UPDATED_FORMAL_NAME)
            .type(UPDATED_TYPE)
            .symbol(UPDATED_SYMBOL)
            .decimalPlaces(UPDATED_DECIMAL_PLACES)
            .extraField(UPDATED_EXTRA_FIELD);
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureMapper.toDto(updatedUnitOfMeasure);

        restUnitOfMeasureMockMvc.perform(put("/api/unit-of-measures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitOfMeasureDTO)))
            .andExpect(status().isOk());

        // Validate the UnitOfMeasure in the database
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureRepository.findAll();
        assertThat(unitOfMeasureList).hasSize(databaseSizeBeforeUpdate);
        UnitOfMeasure testUnitOfMeasure = unitOfMeasureList.get(unitOfMeasureList.size() - 1);
        assertThat(testUnitOfMeasure.getFormalName()).isEqualTo(UPDATED_FORMAL_NAME);
        assertThat(testUnitOfMeasure.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testUnitOfMeasure.getSymbol()).isEqualTo(UPDATED_SYMBOL);
        assertThat(testUnitOfMeasure.getDecimalPlaces()).isEqualTo(UPDATED_DECIMAL_PLACES);
        assertThat(testUnitOfMeasure.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitOfMeasure() throws Exception {
        int databaseSizeBeforeUpdate = unitOfMeasureRepository.findAll().size();

        // Create the UnitOfMeasure
        UnitOfMeasureDTO unitOfMeasureDTO = unitOfMeasureMapper.toDto(unitOfMeasure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitOfMeasureMockMvc.perform(put("/api/unit-of-measures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitOfMeasureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnitOfMeasure in the database
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureRepository.findAll();
        assertThat(unitOfMeasureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnitOfMeasure() throws Exception {
        // Initialize the database
        unitOfMeasureRepository.saveAndFlush(unitOfMeasure);

        int databaseSizeBeforeDelete = unitOfMeasureRepository.findAll().size();

        // Delete the unitOfMeasure
        restUnitOfMeasureMockMvc.perform(delete("/api/unit-of-measures/{id}", unitOfMeasure.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureRepository.findAll();
        assertThat(unitOfMeasureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

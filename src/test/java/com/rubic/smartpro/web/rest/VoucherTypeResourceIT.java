package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.VoucherType;
import com.rubic.smartpro.repository.VoucherTypeRepository;
import com.rubic.smartpro.service.VoucherTypeService;
import com.rubic.smartpro.service.dto.VoucherTypeDTO;
import com.rubic.smartpro.service.mapper.VoucherTypeMapper;

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
 * Integration tests for the {@link VoucherTypeResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VoucherTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private VoucherTypeRepository voucherTypeRepository;

    @Autowired
    private VoucherTypeMapper voucherTypeMapper;

    @Autowired
    private VoucherTypeService voucherTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVoucherTypeMockMvc;

    private VoucherType voucherType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VoucherType createEntity(EntityManager em) {
        VoucherType voucherType = new VoucherType()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .method(DEFAULT_METHOD)
            .description(DEFAULT_DESCRIPTION)
            .extraField(DEFAULT_EXTRA_FIELD);
        return voucherType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VoucherType createUpdatedEntity(EntityManager em) {
        VoucherType voucherType = new VoucherType()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .method(UPDATED_METHOD)
            .description(UPDATED_DESCRIPTION)
            .extraField(UPDATED_EXTRA_FIELD);
        return voucherType;
    }

    @BeforeEach
    public void initTest() {
        voucherType = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoucherType() throws Exception {
        int databaseSizeBeforeCreate = voucherTypeRepository.findAll().size();
        // Create the VoucherType
        VoucherTypeDTO voucherTypeDTO = voucherTypeMapper.toDto(voucherType);
        restVoucherTypeMockMvc.perform(post("/api/voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the VoucherType in the database
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeCreate + 1);
        VoucherType testVoucherType = voucherTypeList.get(voucherTypeList.size() - 1);
        assertThat(testVoucherType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVoucherType.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVoucherType.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testVoucherType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVoucherType.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createVoucherTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voucherTypeRepository.findAll().size();

        // Create the VoucherType with an existing ID
        voucherType.setId(1L);
        VoucherTypeDTO voucherTypeDTO = voucherTypeMapper.toDto(voucherType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoucherTypeMockMvc.perform(post("/api/voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VoucherType in the database
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = voucherTypeRepository.findAll().size();
        // set the field null
        voucherType.setName(null);

        // Create the VoucherType, which fails.
        VoucherTypeDTO voucherTypeDTO = voucherTypeMapper.toDto(voucherType);


        restVoucherTypeMockMvc.perform(post("/api/voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherTypeDTO)))
            .andExpect(status().isBadRequest());

        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVoucherTypes() throws Exception {
        // Initialize the database
        voucherTypeRepository.saveAndFlush(voucherType);

        // Get all the voucherTypeList
        restVoucherTypeMockMvc.perform(get("/api/voucher-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voucherType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getVoucherType() throws Exception {
        // Initialize the database
        voucherTypeRepository.saveAndFlush(voucherType);

        // Get the voucherType
        restVoucherTypeMockMvc.perform(get("/api/voucher-types/{id}", voucherType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(voucherType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingVoucherType() throws Exception {
        // Get the voucherType
        restVoucherTypeMockMvc.perform(get("/api/voucher-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoucherType() throws Exception {
        // Initialize the database
        voucherTypeRepository.saveAndFlush(voucherType);

        int databaseSizeBeforeUpdate = voucherTypeRepository.findAll().size();

        // Update the voucherType
        VoucherType updatedVoucherType = voucherTypeRepository.findById(voucherType.getId()).get();
        // Disconnect from session so that the updates on updatedVoucherType are not directly saved in db
        em.detach(updatedVoucherType);
        updatedVoucherType
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .method(UPDATED_METHOD)
            .description(UPDATED_DESCRIPTION)
            .extraField(UPDATED_EXTRA_FIELD);
        VoucherTypeDTO voucherTypeDTO = voucherTypeMapper.toDto(updatedVoucherType);

        restVoucherTypeMockMvc.perform(put("/api/voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherTypeDTO)))
            .andExpect(status().isOk());

        // Validate the VoucherType in the database
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeUpdate);
        VoucherType testVoucherType = voucherTypeList.get(voucherTypeList.size() - 1);
        assertThat(testVoucherType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVoucherType.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVoucherType.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testVoucherType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVoucherType.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingVoucherType() throws Exception {
        int databaseSizeBeforeUpdate = voucherTypeRepository.findAll().size();

        // Create the VoucherType
        VoucherTypeDTO voucherTypeDTO = voucherTypeMapper.toDto(voucherType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVoucherTypeMockMvc.perform(put("/api/voucher-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(voucherTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VoucherType in the database
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoucherType() throws Exception {
        // Initialize the database
        voucherTypeRepository.saveAndFlush(voucherType);

        int databaseSizeBeforeDelete = voucherTypeRepository.findAll().size();

        // Delete the voucherType
        restVoucherTypeMockMvc.perform(delete("/api/voucher-types/{id}", voucherType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VoucherType> voucherTypeList = voucherTypeRepository.findAll();
        assertThat(voucherTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

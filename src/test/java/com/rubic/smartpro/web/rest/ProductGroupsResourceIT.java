package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.ProductGroups;
import com.rubic.smartpro.repository.ProductGroupsRepository;
import com.rubic.smartpro.service.ProductGroupsService;
import com.rubic.smartpro.service.dto.ProductGroupsDTO;
import com.rubic.smartpro.service.mapper.ProductGroupsMapper;

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
 * Integration tests for the {@link ProductGroupsResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductGroupsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private ProductGroupsRepository productGroupsRepository;

    @Autowired
    private ProductGroupsMapper productGroupsMapper;

    @Autowired
    private ProductGroupsService productGroupsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductGroupsMockMvc;

    private ProductGroups productGroups;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductGroups createEntity(EntityManager em) {
        ProductGroups productGroups = new ProductGroups()
            .name(DEFAULT_NAME)
            .group(DEFAULT_GROUP)
            .quantity(DEFAULT_QUANTITY)
            .extraField(DEFAULT_EXTRA_FIELD);
        return productGroups;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductGroups createUpdatedEntity(EntityManager em) {
        ProductGroups productGroups = new ProductGroups()
            .name(UPDATED_NAME)
            .group(UPDATED_GROUP)
            .quantity(UPDATED_QUANTITY)
            .extraField(UPDATED_EXTRA_FIELD);
        return productGroups;
    }

    @BeforeEach
    public void initTest() {
        productGroups = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductGroups() throws Exception {
        int databaseSizeBeforeCreate = productGroupsRepository.findAll().size();
        // Create the ProductGroups
        ProductGroupsDTO productGroupsDTO = productGroupsMapper.toDto(productGroups);
        restProductGroupsMockMvc.perform(post("/api/product-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productGroupsDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductGroups in the database
        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeCreate + 1);
        ProductGroups testProductGroups = productGroupsList.get(productGroupsList.size() - 1);
        assertThat(testProductGroups.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testProductGroups.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testProductGroups.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testProductGroups.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createProductGroupsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productGroupsRepository.findAll().size();

        // Create the ProductGroups with an existing ID
        productGroups.setId(1L);
        ProductGroupsDTO productGroupsDTO = productGroupsMapper.toDto(productGroups);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductGroupsMockMvc.perform(post("/api/product-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productGroupsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductGroups in the database
        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = productGroupsRepository.findAll().size();
        // set the field null
        productGroups.setName(null);

        // Create the ProductGroups, which fails.
        ProductGroupsDTO productGroupsDTO = productGroupsMapper.toDto(productGroups);


        restProductGroupsMockMvc.perform(post("/api/product-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productGroupsDTO)))
            .andExpect(status().isBadRequest());

        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProductGroups() throws Exception {
        // Initialize the database
        productGroupsRepository.saveAndFlush(productGroups);

        // Get all the productGroupsList
        restProductGroupsMockMvc.perform(get("/api/product-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productGroups.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getProductGroups() throws Exception {
        // Initialize the database
        productGroupsRepository.saveAndFlush(productGroups);

        // Get the productGroups
        restProductGroupsMockMvc.perform(get("/api/product-groups/{id}", productGroups.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productGroups.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingProductGroups() throws Exception {
        // Get the productGroups
        restProductGroupsMockMvc.perform(get("/api/product-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductGroups() throws Exception {
        // Initialize the database
        productGroupsRepository.saveAndFlush(productGroups);

        int databaseSizeBeforeUpdate = productGroupsRepository.findAll().size();

        // Update the productGroups
        ProductGroups updatedProductGroups = productGroupsRepository.findById(productGroups.getId()).get();
        // Disconnect from session so that the updates on updatedProductGroups are not directly saved in db
        em.detach(updatedProductGroups);
        updatedProductGroups
            .name(UPDATED_NAME)
            .group(UPDATED_GROUP)
            .quantity(UPDATED_QUANTITY)
            .extraField(UPDATED_EXTRA_FIELD);
        ProductGroupsDTO productGroupsDTO = productGroupsMapper.toDto(updatedProductGroups);

        restProductGroupsMockMvc.perform(put("/api/product-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productGroupsDTO)))
            .andExpect(status().isOk());

        // Validate the ProductGroups in the database
        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeUpdate);
        ProductGroups testProductGroups = productGroupsList.get(productGroupsList.size() - 1);
        assertThat(testProductGroups.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testProductGroups.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testProductGroups.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testProductGroups.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingProductGroups() throws Exception {
        int databaseSizeBeforeUpdate = productGroupsRepository.findAll().size();

        // Create the ProductGroups
        ProductGroupsDTO productGroupsDTO = productGroupsMapper.toDto(productGroups);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductGroupsMockMvc.perform(put("/api/product-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productGroupsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductGroups in the database
        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductGroups() throws Exception {
        // Initialize the database
        productGroupsRepository.saveAndFlush(productGroups);

        int databaseSizeBeforeDelete = productGroupsRepository.findAll().size();

        // Delete the productGroups
        restProductGroupsMockMvc.perform(delete("/api/product-groups/{id}", productGroups.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductGroups> productGroupsList = productGroupsRepository.findAll();
        assertThat(productGroupsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

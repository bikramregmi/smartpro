package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.Groups;
import com.rubic.smartpro.repository.GroupsRepository;
import com.rubic.smartpro.service.GroupsService;
import com.rubic.smartpro.service.dto.GroupsDTO;
import com.rubic.smartpro.service.mapper.GroupsMapper;

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
 * Integration tests for the {@link GroupsResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GroupsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXTRA_FIELD_1 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_1 = true;

    private static final Boolean DEFAULT_EXTRA_FIELD_2 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_2 = true;

    private static final Boolean DEFAULT_EXTRA_FIELD_3 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_3 = true;

    private static final Boolean DEFAULT_EXTRA_FIELD_4 = false;
    private static final Boolean UPDATED_EXTRA_FIELD_4 = true;

    private static final String DEFAULT_EXTRA_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD_6 = "BBBBBBBBBB";

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private GroupsMapper groupsMapper;

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGroupsMockMvc;

    private Groups groups;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groups createEntity(EntityManager em) {
        Groups groups = new Groups()
            .name(DEFAULT_NAME)
            .group(DEFAULT_GROUP)
            .extraField1(DEFAULT_EXTRA_FIELD_1)
            .extraField2(DEFAULT_EXTRA_FIELD_2)
            .extraField3(DEFAULT_EXTRA_FIELD_3)
            .extraField4(DEFAULT_EXTRA_FIELD_4)
            .extraField5(DEFAULT_EXTRA_FIELD_5)
            .extraField6(DEFAULT_EXTRA_FIELD_6);
        return groups;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groups createUpdatedEntity(EntityManager em) {
        Groups groups = new Groups()
            .name(UPDATED_NAME)
            .group(UPDATED_GROUP)
            .extraField1(UPDATED_EXTRA_FIELD_1)
            .extraField2(UPDATED_EXTRA_FIELD_2)
            .extraField3(UPDATED_EXTRA_FIELD_3)
            .extraField4(UPDATED_EXTRA_FIELD_4)
            .extraField5(UPDATED_EXTRA_FIELD_5)
            .extraField6(UPDATED_EXTRA_FIELD_6);
        return groups;
    }

    @BeforeEach
    public void initTest() {
        groups = createEntity(em);
    }

    @Test
    @Transactional
    public void createGroups() throws Exception {
        int databaseSizeBeforeCreate = groupsRepository.findAll().size();
        // Create the Groups
        GroupsDTO groupsDTO = groupsMapper.toDto(groups);
        restGroupsMockMvc.perform(post("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupsDTO)))
            .andExpect(status().isCreated());

        // Validate the Groups in the database
        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeCreate + 1);
        Groups testGroups = groupsList.get(groupsList.size() - 1);
        assertThat(testGroups.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGroups.getGroup()).isEqualTo(DEFAULT_GROUP);
        assertThat(testGroups.isExtraField1()).isEqualTo(DEFAULT_EXTRA_FIELD_1);
        assertThat(testGroups.isExtraField2()).isEqualTo(DEFAULT_EXTRA_FIELD_2);
        assertThat(testGroups.isExtraField3()).isEqualTo(DEFAULT_EXTRA_FIELD_3);
        assertThat(testGroups.isExtraField4()).isEqualTo(DEFAULT_EXTRA_FIELD_4);
        assertThat(testGroups.getExtraField5()).isEqualTo(DEFAULT_EXTRA_FIELD_5);
        assertThat(testGroups.getExtraField6()).isEqualTo(DEFAULT_EXTRA_FIELD_6);
    }

    @Test
    @Transactional
    public void createGroupsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupsRepository.findAll().size();

        // Create the Groups with an existing ID
        groups.setId(1L);
        GroupsDTO groupsDTO = groupsMapper.toDto(groups);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupsMockMvc.perform(post("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Groups in the database
        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupsRepository.findAll().size();
        // set the field null
        groups.setName(null);

        // Create the Groups, which fails.
        GroupsDTO groupsDTO = groupsMapper.toDto(groups);


        restGroupsMockMvc.perform(post("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupsDTO)))
            .andExpect(status().isBadRequest());

        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGroups() throws Exception {
        // Initialize the database
        groupsRepository.saveAndFlush(groups);

        // Get all the groupsList
        restGroupsMockMvc.perform(get("/api/groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groups.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)))
            .andExpect(jsonPath("$.[*].extraField1").value(hasItem(DEFAULT_EXTRA_FIELD_1.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField2").value(hasItem(DEFAULT_EXTRA_FIELD_2.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField3").value(hasItem(DEFAULT_EXTRA_FIELD_3.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField4").value(hasItem(DEFAULT_EXTRA_FIELD_4.booleanValue())))
            .andExpect(jsonPath("$.[*].extraField5").value(hasItem(DEFAULT_EXTRA_FIELD_5)))
            .andExpect(jsonPath("$.[*].extraField6").value(hasItem(DEFAULT_EXTRA_FIELD_6)));
    }
    
    @Test
    @Transactional
    public void getGroups() throws Exception {
        // Initialize the database
        groupsRepository.saveAndFlush(groups);

        // Get the groups
        restGroupsMockMvc.perform(get("/api/groups/{id}", groups.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groups.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP))
            .andExpect(jsonPath("$.extraField1").value(DEFAULT_EXTRA_FIELD_1.booleanValue()))
            .andExpect(jsonPath("$.extraField2").value(DEFAULT_EXTRA_FIELD_2.booleanValue()))
            .andExpect(jsonPath("$.extraField3").value(DEFAULT_EXTRA_FIELD_3.booleanValue()))
            .andExpect(jsonPath("$.extraField4").value(DEFAULT_EXTRA_FIELD_4.booleanValue()))
            .andExpect(jsonPath("$.extraField5").value(DEFAULT_EXTRA_FIELD_5))
            .andExpect(jsonPath("$.extraField6").value(DEFAULT_EXTRA_FIELD_6));
    }
    @Test
    @Transactional
    public void getNonExistingGroups() throws Exception {
        // Get the groups
        restGroupsMockMvc.perform(get("/api/groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGroups() throws Exception {
        // Initialize the database
        groupsRepository.saveAndFlush(groups);

        int databaseSizeBeforeUpdate = groupsRepository.findAll().size();

        // Update the groups
        Groups updatedGroups = groupsRepository.findById(groups.getId()).get();
        // Disconnect from session so that the updates on updatedGroups are not directly saved in db
        em.detach(updatedGroups);
        updatedGroups
            .name(UPDATED_NAME)
            .group(UPDATED_GROUP)
            .extraField1(UPDATED_EXTRA_FIELD_1)
            .extraField2(UPDATED_EXTRA_FIELD_2)
            .extraField3(UPDATED_EXTRA_FIELD_3)
            .extraField4(UPDATED_EXTRA_FIELD_4)
            .extraField5(UPDATED_EXTRA_FIELD_5)
            .extraField6(UPDATED_EXTRA_FIELD_6);
        GroupsDTO groupsDTO = groupsMapper.toDto(updatedGroups);

        restGroupsMockMvc.perform(put("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupsDTO)))
            .andExpect(status().isOk());

        // Validate the Groups in the database
        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeUpdate);
        Groups testGroups = groupsList.get(groupsList.size() - 1);
        assertThat(testGroups.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGroups.getGroup()).isEqualTo(UPDATED_GROUP);
        assertThat(testGroups.isExtraField1()).isEqualTo(UPDATED_EXTRA_FIELD_1);
        assertThat(testGroups.isExtraField2()).isEqualTo(UPDATED_EXTRA_FIELD_2);
        assertThat(testGroups.isExtraField3()).isEqualTo(UPDATED_EXTRA_FIELD_3);
        assertThat(testGroups.isExtraField4()).isEqualTo(UPDATED_EXTRA_FIELD_4);
        assertThat(testGroups.getExtraField5()).isEqualTo(UPDATED_EXTRA_FIELD_5);
        assertThat(testGroups.getExtraField6()).isEqualTo(UPDATED_EXTRA_FIELD_6);
    }

    @Test
    @Transactional
    public void updateNonExistingGroups() throws Exception {
        int databaseSizeBeforeUpdate = groupsRepository.findAll().size();

        // Create the Groups
        GroupsDTO groupsDTO = groupsMapper.toDto(groups);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupsMockMvc.perform(put("/api/groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Groups in the database
        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGroups() throws Exception {
        // Initialize the database
        groupsRepository.saveAndFlush(groups);

        int databaseSizeBeforeDelete = groupsRepository.findAll().size();

        // Delete the groups
        restGroupsMockMvc.perform(delete("/api/groups/{id}", groups.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Groups> groupsList = groupsRepository.findAll();
        assertThat(groupsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

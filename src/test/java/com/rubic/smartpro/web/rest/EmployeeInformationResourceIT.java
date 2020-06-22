package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.EmployeeInformation;
import com.rubic.smartpro.repository.EmployeeInformationRepository;
import com.rubic.smartpro.service.EmployeeInformationService;
import com.rubic.smartpro.service.dto.EmployeeInformationDTO;
import com.rubic.smartpro.service.mapper.EmployeeInformationMapper;

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
 * Integration tests for the {@link EmployeeInformationResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeeInformationResourceIT {

    private static final String DEFAULT_DOB = "AAAAAAAAAA";
    private static final String UPDATED_DOB = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_MARRIED = false;
    private static final Boolean UPDATED_IS_MARRIED = true;

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSLINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSLINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSLINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSLINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCY_CONTACT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCY_CONTACT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_JOINING_DATE = "AAAAAAAAAA";
    private static final String UPDATED_JOINING_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    @Autowired
    private EmployeeInformationRepository employeeInformationRepository;

    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    @Autowired
    private EmployeeInformationService employeeInformationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeeInformationMockMvc;

    private EmployeeInformation employeeInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmployeeInformation createEntity(EntityManager em) {
        EmployeeInformation employeeInformation = new EmployeeInformation()
            .dob(DEFAULT_DOB)
            .gender(DEFAULT_GENDER)
            .isMarried(DEFAULT_IS_MARRIED)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .parentName(DEFAULT_PARENT_NAME)
            .addressline1(DEFAULT_ADDRESSLINE_1)
            .addressline2(DEFAULT_ADDRESSLINE_2)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .mobileNumber(DEFAULT_MOBILE_NUMBER)
            .emergencyContactNumber(DEFAULT_EMERGENCY_CONTACT_NUMBER)
            .joiningDate(DEFAULT_JOINING_DATE)
            .designation(DEFAULT_DESIGNATION)
            .panNumber(DEFAULT_PAN_NUMBER)
            .documentNumber(DEFAULT_DOCUMENT_NUMBER)
            .extraField(DEFAULT_EXTRA_FIELD);
        return employeeInformation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmployeeInformation createUpdatedEntity(EntityManager em) {
        EmployeeInformation employeeInformation = new EmployeeInformation()
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .isMarried(UPDATED_IS_MARRIED)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .parentName(UPDATED_PARENT_NAME)
            .addressline1(UPDATED_ADDRESSLINE_1)
            .addressline2(UPDATED_ADDRESSLINE_2)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .joiningDate(UPDATED_JOINING_DATE)
            .designation(UPDATED_DESIGNATION)
            .panNumber(UPDATED_PAN_NUMBER)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .extraField(UPDATED_EXTRA_FIELD);
        return employeeInformation;
    }

    @BeforeEach
    public void initTest() {
        employeeInformation = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeeInformation() throws Exception {
        int databaseSizeBeforeCreate = employeeInformationRepository.findAll().size();
        // Create the EmployeeInformation
        EmployeeInformationDTO employeeInformationDTO = employeeInformationMapper.toDto(employeeInformation);
        restEmployeeInformationMockMvc.perform(post("/api/employee-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeInformationDTO)))
            .andExpect(status().isCreated());

        // Validate the EmployeeInformation in the database
        List<EmployeeInformation> employeeInformationList = employeeInformationRepository.findAll();
        assertThat(employeeInformationList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeInformation testEmployeeInformation = employeeInformationList.get(employeeInformationList.size() - 1);
        assertThat(testEmployeeInformation.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testEmployeeInformation.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testEmployeeInformation.isIsMarried()).isEqualTo(DEFAULT_IS_MARRIED);
        assertThat(testEmployeeInformation.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testEmployeeInformation.getParentName()).isEqualTo(DEFAULT_PARENT_NAME);
        assertThat(testEmployeeInformation.getAddressline1()).isEqualTo(DEFAULT_ADDRESSLINE_1);
        assertThat(testEmployeeInformation.getAddressline2()).isEqualTo(DEFAULT_ADDRESSLINE_2);
        assertThat(testEmployeeInformation.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testEmployeeInformation.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testEmployeeInformation.getEmergencyContactNumber()).isEqualTo(DEFAULT_EMERGENCY_CONTACT_NUMBER);
        assertThat(testEmployeeInformation.getJoiningDate()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testEmployeeInformation.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testEmployeeInformation.getPanNumber()).isEqualTo(DEFAULT_PAN_NUMBER);
        assertThat(testEmployeeInformation.getDocumentNumber()).isEqualTo(DEFAULT_DOCUMENT_NUMBER);
        assertThat(testEmployeeInformation.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void createEmployeeInformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeInformationRepository.findAll().size();

        // Create the EmployeeInformation with an existing ID
        employeeInformation.setId(1L);
        EmployeeInformationDTO employeeInformationDTO = employeeInformationMapper.toDto(employeeInformation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeInformationMockMvc.perform(post("/api/employee-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeInformationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeInformation in the database
        List<EmployeeInformation> employeeInformationList = employeeInformationRepository.findAll();
        assertThat(employeeInformationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmployeeInformations() throws Exception {
        // Initialize the database
        employeeInformationRepository.saveAndFlush(employeeInformation);

        // Get all the employeeInformationList
        restEmployeeInformationMockMvc.perform(get("/api/employee-informations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].isMarried").value(hasItem(DEFAULT_IS_MARRIED.booleanValue())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP)))
            .andExpect(jsonPath("$.[*].parentName").value(hasItem(DEFAULT_PARENT_NAME)))
            .andExpect(jsonPath("$.[*].addressline1").value(hasItem(DEFAULT_ADDRESSLINE_1)))
            .andExpect(jsonPath("$.[*].addressline2").value(hasItem(DEFAULT_ADDRESSLINE_2)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER)))
            .andExpect(jsonPath("$.[*].emergencyContactNumber").value(hasItem(DEFAULT_EMERGENCY_CONTACT_NUMBER)))
            .andExpect(jsonPath("$.[*].joiningDate").value(hasItem(DEFAULT_JOINING_DATE)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)))
            .andExpect(jsonPath("$.[*].panNumber").value(hasItem(DEFAULT_PAN_NUMBER)))
            .andExpect(jsonPath("$.[*].documentNumber").value(hasItem(DEFAULT_DOCUMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)));
    }
    
    @Test
    @Transactional
    public void getEmployeeInformation() throws Exception {
        // Initialize the database
        employeeInformationRepository.saveAndFlush(employeeInformation);

        // Get the employeeInformation
        restEmployeeInformationMockMvc.perform(get("/api/employee-informations/{id}", employeeInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employeeInformation.getId().intValue()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.isMarried").value(DEFAULT_IS_MARRIED.booleanValue()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP))
            .andExpect(jsonPath("$.parentName").value(DEFAULT_PARENT_NAME))
            .andExpect(jsonPath("$.addressline1").value(DEFAULT_ADDRESSLINE_1))
            .andExpect(jsonPath("$.addressline2").value(DEFAULT_ADDRESSLINE_2))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER))
            .andExpect(jsonPath("$.emergencyContactNumber").value(DEFAULT_EMERGENCY_CONTACT_NUMBER))
            .andExpect(jsonPath("$.joiningDate").value(DEFAULT_JOINING_DATE))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION))
            .andExpect(jsonPath("$.panNumber").value(DEFAULT_PAN_NUMBER))
            .andExpect(jsonPath("$.documentNumber").value(DEFAULT_DOCUMENT_NUMBER))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD));
    }
    @Test
    @Transactional
    public void getNonExistingEmployeeInformation() throws Exception {
        // Get the employeeInformation
        restEmployeeInformationMockMvc.perform(get("/api/employee-informations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeeInformation() throws Exception {
        // Initialize the database
        employeeInformationRepository.saveAndFlush(employeeInformation);

        int databaseSizeBeforeUpdate = employeeInformationRepository.findAll().size();

        // Update the employeeInformation
        EmployeeInformation updatedEmployeeInformation = employeeInformationRepository.findById(employeeInformation.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeeInformation are not directly saved in db
        em.detach(updatedEmployeeInformation);
        updatedEmployeeInformation
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .isMarried(UPDATED_IS_MARRIED)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .parentName(UPDATED_PARENT_NAME)
            .addressline1(UPDATED_ADDRESSLINE_1)
            .addressline2(UPDATED_ADDRESSLINE_2)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .mobileNumber(UPDATED_MOBILE_NUMBER)
            .emergencyContactNumber(UPDATED_EMERGENCY_CONTACT_NUMBER)
            .joiningDate(UPDATED_JOINING_DATE)
            .designation(UPDATED_DESIGNATION)
            .panNumber(UPDATED_PAN_NUMBER)
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .extraField(UPDATED_EXTRA_FIELD);
        EmployeeInformationDTO employeeInformationDTO = employeeInformationMapper.toDto(updatedEmployeeInformation);

        restEmployeeInformationMockMvc.perform(put("/api/employee-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeInformationDTO)))
            .andExpect(status().isOk());

        // Validate the EmployeeInformation in the database
        List<EmployeeInformation> employeeInformationList = employeeInformationRepository.findAll();
        assertThat(employeeInformationList).hasSize(databaseSizeBeforeUpdate);
        EmployeeInformation testEmployeeInformation = employeeInformationList.get(employeeInformationList.size() - 1);
        assertThat(testEmployeeInformation.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testEmployeeInformation.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testEmployeeInformation.isIsMarried()).isEqualTo(UPDATED_IS_MARRIED);
        assertThat(testEmployeeInformation.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testEmployeeInformation.getParentName()).isEqualTo(UPDATED_PARENT_NAME);
        assertThat(testEmployeeInformation.getAddressline1()).isEqualTo(UPDATED_ADDRESSLINE_1);
        assertThat(testEmployeeInformation.getAddressline2()).isEqualTo(UPDATED_ADDRESSLINE_2);
        assertThat(testEmployeeInformation.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testEmployeeInformation.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testEmployeeInformation.getEmergencyContactNumber()).isEqualTo(UPDATED_EMERGENCY_CONTACT_NUMBER);
        assertThat(testEmployeeInformation.getJoiningDate()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testEmployeeInformation.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testEmployeeInformation.getPanNumber()).isEqualTo(UPDATED_PAN_NUMBER);
        assertThat(testEmployeeInformation.getDocumentNumber()).isEqualTo(UPDATED_DOCUMENT_NUMBER);
        assertThat(testEmployeeInformation.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeeInformation() throws Exception {
        int databaseSizeBeforeUpdate = employeeInformationRepository.findAll().size();

        // Create the EmployeeInformation
        EmployeeInformationDTO employeeInformationDTO = employeeInformationMapper.toDto(employeeInformation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeInformationMockMvc.perform(put("/api/employee-informations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeInformationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeInformation in the database
        List<EmployeeInformation> employeeInformationList = employeeInformationRepository.findAll();
        assertThat(employeeInformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployeeInformation() throws Exception {
        // Initialize the database
        employeeInformationRepository.saveAndFlush(employeeInformation);

        int databaseSizeBeforeDelete = employeeInformationRepository.findAll().size();

        // Delete the employeeInformation
        restEmployeeInformationMockMvc.perform(delete("/api/employee-informations/{id}", employeeInformation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmployeeInformation> employeeInformationList = employeeInformationRepository.findAll();
        assertThat(employeeInformationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

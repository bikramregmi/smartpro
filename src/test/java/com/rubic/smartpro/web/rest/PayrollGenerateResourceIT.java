package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.PayrollGenerate;
import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.domain.EmployeeSalary;
import com.rubic.smartpro.repository.PayrollGenerateRepository;
import com.rubic.smartpro.service.PayrollGenerateService;
import com.rubic.smartpro.service.dto.PayrollGenerateDTO;
import com.rubic.smartpro.service.mapper.PayrollGenerateMapper;
import com.rubic.smartpro.service.dto.PayrollGenerateCriteria;
import com.rubic.smartpro.service.PayrollGenerateQueryService;

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
 * Integration tests for the {@link PayrollGenerateResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PayrollGenerateResourceIT {

    private static final String DEFAULT_SALARY_MONTH = "AAAAAAAAAA";
    private static final String UPDATED_SALARY_MONTH = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAX = "AAAAAAAAAA";
    private static final String UPDATED_TAX = "BBBBBBBBBB";

    private static final String DEFAULT_PF = "AAAAAAAAAA";
    private static final String UPDATED_PF = "BBBBBBBBBB";

    @Autowired
    private PayrollGenerateRepository payrollGenerateRepository;

    @Autowired
    private PayrollGenerateMapper payrollGenerateMapper;

    @Autowired
    private PayrollGenerateService payrollGenerateService;

    @Autowired
    private PayrollGenerateQueryService payrollGenerateQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayrollGenerateMockMvc;

    private PayrollGenerate payrollGenerate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayrollGenerate createEntity(EntityManager em) {
        PayrollGenerate payrollGenerate = new PayrollGenerate()
            .salaryMonth(DEFAULT_SALARY_MONTH)
            .description(DEFAULT_DESCRIPTION)
            .tax(DEFAULT_TAX)
            .pf(DEFAULT_PF);
        return payrollGenerate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PayrollGenerate createUpdatedEntity(EntityManager em) {
        PayrollGenerate payrollGenerate = new PayrollGenerate()
            .salaryMonth(UPDATED_SALARY_MONTH)
            .description(UPDATED_DESCRIPTION)
            .tax(UPDATED_TAX)
            .pf(UPDATED_PF);
        return payrollGenerate;
    }

    @BeforeEach
    public void initTest() {
        payrollGenerate = createEntity(em);
    }

    @Test
    @Transactional
    public void createPayrollGenerate() throws Exception {
        int databaseSizeBeforeCreate = payrollGenerateRepository.findAll().size();
        // Create the PayrollGenerate
        PayrollGenerateDTO payrollGenerateDTO = payrollGenerateMapper.toDto(payrollGenerate);
        restPayrollGenerateMockMvc.perform(post("/api/payroll-generates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payrollGenerateDTO)))
            .andExpect(status().isCreated());

        // Validate the PayrollGenerate in the database
        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeCreate + 1);
        PayrollGenerate testPayrollGenerate = payrollGenerateList.get(payrollGenerateList.size() - 1);
        assertThat(testPayrollGenerate.getSalaryMonth()).isEqualTo(DEFAULT_SALARY_MONTH);
        assertThat(testPayrollGenerate.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPayrollGenerate.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testPayrollGenerate.getPf()).isEqualTo(DEFAULT_PF);
    }

    @Test
    @Transactional
    public void createPayrollGenerateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = payrollGenerateRepository.findAll().size();

        // Create the PayrollGenerate with an existing ID
        payrollGenerate.setId(1L);
        PayrollGenerateDTO payrollGenerateDTO = payrollGenerateMapper.toDto(payrollGenerate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayrollGenerateMockMvc.perform(post("/api/payroll-generates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payrollGenerateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PayrollGenerate in the database
        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSalaryMonthIsRequired() throws Exception {
        int databaseSizeBeforeTest = payrollGenerateRepository.findAll().size();
        // set the field null
        payrollGenerate.setSalaryMonth(null);

        // Create the PayrollGenerate, which fails.
        PayrollGenerateDTO payrollGenerateDTO = payrollGenerateMapper.toDto(payrollGenerate);


        restPayrollGenerateMockMvc.perform(post("/api/payroll-generates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payrollGenerateDTO)))
            .andExpect(status().isBadRequest());

        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPayrollGenerates() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payrollGenerate.getId().intValue())))
            .andExpect(jsonPath("$.[*].salaryMonth").value(hasItem(DEFAULT_SALARY_MONTH)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF)));
    }
    
    @Test
    @Transactional
    public void getPayrollGenerate() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get the payrollGenerate
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates/{id}", payrollGenerate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payrollGenerate.getId().intValue()))
            .andExpect(jsonPath("$.salaryMonth").value(DEFAULT_SALARY_MONTH))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.pf").value(DEFAULT_PF));
    }


    @Test
    @Transactional
    public void getPayrollGeneratesByIdFiltering() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        Long id = payrollGenerate.getId();

        defaultPayrollGenerateShouldBeFound("id.equals=" + id);
        defaultPayrollGenerateShouldNotBeFound("id.notEquals=" + id);

        defaultPayrollGenerateShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPayrollGenerateShouldNotBeFound("id.greaterThan=" + id);

        defaultPayrollGenerateShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPayrollGenerateShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth equals to DEFAULT_SALARY_MONTH
        defaultPayrollGenerateShouldBeFound("salaryMonth.equals=" + DEFAULT_SALARY_MONTH);

        // Get all the payrollGenerateList where salaryMonth equals to UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.equals=" + UPDATED_SALARY_MONTH);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth not equals to DEFAULT_SALARY_MONTH
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.notEquals=" + DEFAULT_SALARY_MONTH);

        // Get all the payrollGenerateList where salaryMonth not equals to UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldBeFound("salaryMonth.notEquals=" + UPDATED_SALARY_MONTH);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthIsInShouldWork() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth in DEFAULT_SALARY_MONTH or UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldBeFound("salaryMonth.in=" + DEFAULT_SALARY_MONTH + "," + UPDATED_SALARY_MONTH);

        // Get all the payrollGenerateList where salaryMonth equals to UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.in=" + UPDATED_SALARY_MONTH);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth is not null
        defaultPayrollGenerateShouldBeFound("salaryMonth.specified=true");

        // Get all the payrollGenerateList where salaryMonth is null
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.specified=false");
    }
                @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth contains DEFAULT_SALARY_MONTH
        defaultPayrollGenerateShouldBeFound("salaryMonth.contains=" + DEFAULT_SALARY_MONTH);

        // Get all the payrollGenerateList where salaryMonth contains UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.contains=" + UPDATED_SALARY_MONTH);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesBySalaryMonthNotContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where salaryMonth does not contain DEFAULT_SALARY_MONTH
        defaultPayrollGenerateShouldNotBeFound("salaryMonth.doesNotContain=" + DEFAULT_SALARY_MONTH);

        // Get all the payrollGenerateList where salaryMonth does not contain UPDATED_SALARY_MONTH
        defaultPayrollGenerateShouldBeFound("salaryMonth.doesNotContain=" + UPDATED_SALARY_MONTH);
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description equals to DEFAULT_DESCRIPTION
        defaultPayrollGenerateShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the payrollGenerateList where description equals to UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description not equals to DEFAULT_DESCRIPTION
        defaultPayrollGenerateShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the payrollGenerateList where description not equals to UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the payrollGenerateList where description equals to UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description is not null
        defaultPayrollGenerateShouldBeFound("description.specified=true");

        // Get all the payrollGenerateList where description is null
        defaultPayrollGenerateShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description contains DEFAULT_DESCRIPTION
        defaultPayrollGenerateShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the payrollGenerateList where description contains UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where description does not contain DEFAULT_DESCRIPTION
        defaultPayrollGenerateShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the payrollGenerateList where description does not contain UPDATED_DESCRIPTION
        defaultPayrollGenerateShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax equals to DEFAULT_TAX
        defaultPayrollGenerateShouldBeFound("tax.equals=" + DEFAULT_TAX);

        // Get all the payrollGenerateList where tax equals to UPDATED_TAX
        defaultPayrollGenerateShouldNotBeFound("tax.equals=" + UPDATED_TAX);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxIsNotEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax not equals to DEFAULT_TAX
        defaultPayrollGenerateShouldNotBeFound("tax.notEquals=" + DEFAULT_TAX);

        // Get all the payrollGenerateList where tax not equals to UPDATED_TAX
        defaultPayrollGenerateShouldBeFound("tax.notEquals=" + UPDATED_TAX);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxIsInShouldWork() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax in DEFAULT_TAX or UPDATED_TAX
        defaultPayrollGenerateShouldBeFound("tax.in=" + DEFAULT_TAX + "," + UPDATED_TAX);

        // Get all the payrollGenerateList where tax equals to UPDATED_TAX
        defaultPayrollGenerateShouldNotBeFound("tax.in=" + UPDATED_TAX);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax is not null
        defaultPayrollGenerateShouldBeFound("tax.specified=true");

        // Get all the payrollGenerateList where tax is null
        defaultPayrollGenerateShouldNotBeFound("tax.specified=false");
    }
                @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax contains DEFAULT_TAX
        defaultPayrollGenerateShouldBeFound("tax.contains=" + DEFAULT_TAX);

        // Get all the payrollGenerateList where tax contains UPDATED_TAX
        defaultPayrollGenerateShouldNotBeFound("tax.contains=" + UPDATED_TAX);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByTaxNotContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where tax does not contain DEFAULT_TAX
        defaultPayrollGenerateShouldNotBeFound("tax.doesNotContain=" + DEFAULT_TAX);

        // Get all the payrollGenerateList where tax does not contain UPDATED_TAX
        defaultPayrollGenerateShouldBeFound("tax.doesNotContain=" + UPDATED_TAX);
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesByPfIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf equals to DEFAULT_PF
        defaultPayrollGenerateShouldBeFound("pf.equals=" + DEFAULT_PF);

        // Get all the payrollGenerateList where pf equals to UPDATED_PF
        defaultPayrollGenerateShouldNotBeFound("pf.equals=" + UPDATED_PF);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByPfIsNotEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf not equals to DEFAULT_PF
        defaultPayrollGenerateShouldNotBeFound("pf.notEquals=" + DEFAULT_PF);

        // Get all the payrollGenerateList where pf not equals to UPDATED_PF
        defaultPayrollGenerateShouldBeFound("pf.notEquals=" + UPDATED_PF);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByPfIsInShouldWork() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf in DEFAULT_PF or UPDATED_PF
        defaultPayrollGenerateShouldBeFound("pf.in=" + DEFAULT_PF + "," + UPDATED_PF);

        // Get all the payrollGenerateList where pf equals to UPDATED_PF
        defaultPayrollGenerateShouldNotBeFound("pf.in=" + UPDATED_PF);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByPfIsNullOrNotNull() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf is not null
        defaultPayrollGenerateShouldBeFound("pf.specified=true");

        // Get all the payrollGenerateList where pf is null
        defaultPayrollGenerateShouldNotBeFound("pf.specified=false");
    }
                @Test
    @Transactional
    public void getAllPayrollGeneratesByPfContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf contains DEFAULT_PF
        defaultPayrollGenerateShouldBeFound("pf.contains=" + DEFAULT_PF);

        // Get all the payrollGenerateList where pf contains UPDATED_PF
        defaultPayrollGenerateShouldNotBeFound("pf.contains=" + UPDATED_PF);
    }

    @Test
    @Transactional
    public void getAllPayrollGeneratesByPfNotContainsSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        // Get all the payrollGenerateList where pf does not contain DEFAULT_PF
        defaultPayrollGenerateShouldNotBeFound("pf.doesNotContain=" + DEFAULT_PF);

        // Get all the payrollGenerateList where pf does not contain UPDATED_PF
        defaultPayrollGenerateShouldBeFound("pf.doesNotContain=" + UPDATED_PF);
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesByEmployeeIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);
        Employee employee = EmployeeResourceIT.createEntity(em);
        em.persist(employee);
        em.flush();
        payrollGenerate.setEmployee(employee);
        payrollGenerateRepository.saveAndFlush(payrollGenerate);
        Long employeeId = employee.getId();

        // Get all the payrollGenerateList where employee equals to employeeId
        defaultPayrollGenerateShouldBeFound("employeeId.equals=" + employeeId);

        // Get all the payrollGenerateList where employee equals to employeeId + 1
        defaultPayrollGenerateShouldNotBeFound("employeeId.equals=" + (employeeId + 1));
    }


    @Test
    @Transactional
    public void getAllPayrollGeneratesByEmployeeSalaryIsEqualToSomething() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);
        EmployeeSalary employeeSalary = EmployeeSalaryResourceIT.createEntity(em);
        em.persist(employeeSalary);
        em.flush();
        payrollGenerate.setEmployeeSalary(employeeSalary);
        payrollGenerateRepository.saveAndFlush(payrollGenerate);
        Long employeeSalaryId = employeeSalary.getId();

        // Get all the payrollGenerateList where employeeSalary equals to employeeSalaryId
        defaultPayrollGenerateShouldBeFound("employeeSalaryId.equals=" + employeeSalaryId);

        // Get all the payrollGenerateList where employeeSalary equals to employeeSalaryId + 1
        defaultPayrollGenerateShouldNotBeFound("employeeSalaryId.equals=" + (employeeSalaryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPayrollGenerateShouldBeFound(String filter) throws Exception {
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payrollGenerate.getId().intValue())))
            .andExpect(jsonPath("$.[*].salaryMonth").value(hasItem(DEFAULT_SALARY_MONTH)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF)));

        // Check, that the count call also returns 1
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPayrollGenerateShouldNotBeFound(String filter) throws Exception {
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPayrollGenerate() throws Exception {
        // Get the payrollGenerate
        restPayrollGenerateMockMvc.perform(get("/api/payroll-generates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePayrollGenerate() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        int databaseSizeBeforeUpdate = payrollGenerateRepository.findAll().size();

        // Update the payrollGenerate
        PayrollGenerate updatedPayrollGenerate = payrollGenerateRepository.findById(payrollGenerate.getId()).get();
        // Disconnect from session so that the updates on updatedPayrollGenerate are not directly saved in db
        em.detach(updatedPayrollGenerate);
        updatedPayrollGenerate
            .salaryMonth(UPDATED_SALARY_MONTH)
            .description(UPDATED_DESCRIPTION)
            .tax(UPDATED_TAX)
            .pf(UPDATED_PF);
        PayrollGenerateDTO payrollGenerateDTO = payrollGenerateMapper.toDto(updatedPayrollGenerate);

        restPayrollGenerateMockMvc.perform(put("/api/payroll-generates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payrollGenerateDTO)))
            .andExpect(status().isOk());

        // Validate the PayrollGenerate in the database
        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeUpdate);
        PayrollGenerate testPayrollGenerate = payrollGenerateList.get(payrollGenerateList.size() - 1);
        assertThat(testPayrollGenerate.getSalaryMonth()).isEqualTo(UPDATED_SALARY_MONTH);
        assertThat(testPayrollGenerate.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPayrollGenerate.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testPayrollGenerate.getPf()).isEqualTo(UPDATED_PF);
    }

    @Test
    @Transactional
    public void updateNonExistingPayrollGenerate() throws Exception {
        int databaseSizeBeforeUpdate = payrollGenerateRepository.findAll().size();

        // Create the PayrollGenerate
        PayrollGenerateDTO payrollGenerateDTO = payrollGenerateMapper.toDto(payrollGenerate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayrollGenerateMockMvc.perform(put("/api/payroll-generates")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(payrollGenerateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PayrollGenerate in the database
        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePayrollGenerate() throws Exception {
        // Initialize the database
        payrollGenerateRepository.saveAndFlush(payrollGenerate);

        int databaseSizeBeforeDelete = payrollGenerateRepository.findAll().size();

        // Delete the payrollGenerate
        restPayrollGenerateMockMvc.perform(delete("/api/payroll-generates/{id}", payrollGenerate.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PayrollGenerate> payrollGenerateList = payrollGenerateRepository.findAll();
        assertThat(payrollGenerateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

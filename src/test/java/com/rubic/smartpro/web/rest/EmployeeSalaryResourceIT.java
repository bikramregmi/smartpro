/*
package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.EmployeeSalary;
import com.rubic.smartpro.repository.EmployeeSalaryRepository;
import com.rubic.smartpro.service.EmployeeSalaryService;
import com.rubic.smartpro.service.dto.EmployeeSalaryDTO;
import com.rubic.smartpro.service.mapper.EmployeeSalaryMapper;

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

*/
/**
 * Integration tests for the {@link EmployeeSalaryResource} REST controller.
 *//*

@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeeSalaryResourceIT {

    private static final long DEFAULT_BASIC_SALARY = "AAAAAAAAAA";
    private static final long UPDATED_BASIC_SALARY = "BBBBBBBBBB";

    private static final String DEFAULT_ALLOWANCE = "AAAAAAAAAA";
    private static final String UPDATED_ALLOWANCE = "BBBBBBBBBB";

    private static final String DEFAULT_OT = "AAAAAAAAAA";
    private static final String UPDATED_OT = "BBBBBBBBBB";

    private static final String DEFAULT_BONUS = "AAAAAAAAAA";
    private static final String UPDATED_BONUS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAX = "AAAAAAAAAA";
    private static final String UPDATED_TAX = "BBBBBBBBBB";

    private static final String DEFAULT_PF = "AAAAAAAAAA";
    private static final String UPDATED_PF = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA = "BBBBBBBBBB";

    @Autowired
    private EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private EmployeeSalaryMapper employeeSalaryMapper;

    @Autowired
    private EmployeeSalaryService employeeSalaryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeeSalaryMockMvc;

    private EmployeeSalary employeeSalary;

    */
/**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     *//*

    public static EmployeeSalary createEntity(EntityManager em) {
        EmployeeSalary employeeSalary = new EmployeeSalary()
            .basicSalary(DEFAULT_BASIC_SALARY)
            .allowance(DEFAULT_ALLOWANCE)
            .ot(DEFAULT_OT)
            .bonus(DEFAULT_BONUS)
            .description(DEFAULT_DESCRIPTION)
            .tax(DEFAULT_TAX)
            .pf(DEFAULT_PF)
            .extra(DEFAULT_EXTRA);
        return employeeSalary;
    }
    */
/**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     *//*

    public static EmployeeSalary createUpdatedEntity(EntityManager em) {
        EmployeeSalary employeeSalary = new EmployeeSalary()
            .basicSalary(UPDATED_BASIC_SALARY)
            .allowance(UPDATED_ALLOWANCE)
            .ot(UPDATED_OT)
            .bonus(UPDATED_BONUS)
            .description(UPDATED_DESCRIPTION)
            .tax(UPDATED_TAX)
            .pf(UPDATED_PF)
            .extra(UPDATED_EXTRA);
        return employeeSalary;
    }

    @BeforeEach
    public void initTest() {
        employeeSalary = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeeSalary() throws Exception {
        int databaseSizeBeforeCreate = employeeSalaryRepository.findAll().size();
        // Create the EmployeeSalary
        EmployeeSalaryDTO employeeSalaryDTO = employeeSalaryMapper.toDto(employeeSalary);
        restEmployeeSalaryMockMvc.perform(post("/api/employee-salaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeSalaryDTO)))
            .andExpect(status().isCreated());

        // Validate the EmployeeSalary in the database
        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findAll();
        assertThat(employeeSalaryList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeSalary testEmployeeSalary = employeeSalaryList.get(employeeSalaryList.size() - 1);
        assertThat(testEmployeeSalary.getBasicSalary()).isEqualTo(DEFAULT_BASIC_SALARY);
        assertThat(testEmployeeSalary.getAllowance()).isEqualTo(DEFAULT_ALLOWANCE);
        assertThat(testEmployeeSalary.getOt()).isEqualTo(DEFAULT_OT);
        assertThat(testEmployeeSalary.getBonus()).isEqualTo(DEFAULT_BONUS);
        assertThat(testEmployeeSalary.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEmployeeSalary.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testEmployeeSalary.getPf()).isEqualTo(DEFAULT_PF);
        assertThat(testEmployeeSalary.getExtra()).isEqualTo(DEFAULT_EXTRA);
    }

    @Test
    @Transactional
    public void createEmployeeSalaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeSalaryRepository.findAll().size();

        // Create the EmployeeSalary with an existing ID
        employeeSalary.setId(1L);
        EmployeeSalaryDTO employeeSalaryDTO = employeeSalaryMapper.toDto(employeeSalary);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeSalaryMockMvc.perform(post("/api/employee-salaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeSalaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeSalary in the database
        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findAll();
        assertThat(employeeSalaryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmployeeSalaries() throws Exception {
        // Initialize the database
        employeeSalaryRepository.saveAndFlush(employeeSalary);

        // Get all the employeeSalaryList
        restEmployeeSalaryMockMvc.perform(get("/api/employee-salaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeSalary.getId().intValue())))
            .andExpect(jsonPath("$.[*].basicSalary").value(hasItem(DEFAULT_BASIC_SALARY)))
            .andExpect(jsonPath("$.[*].allowance").value(hasItem(DEFAULT_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].ot").value(hasItem(DEFAULT_OT)))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].pf").value(hasItem(DEFAULT_PF)))
            .andExpect(jsonPath("$.[*].extra").value(hasItem(DEFAULT_EXTRA)));
    }

    @Test
    @Transactional
    public void getEmployeeSalary() throws Exception {
        // Initialize the database
        employeeSalaryRepository.saveAndFlush(employeeSalary);

        // Get the employeeSalary
        restEmployeeSalaryMockMvc.perform(get("/api/employee-salaries/{id}", employeeSalary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employeeSalary.getId().intValue()))
            .andExpect(jsonPath("$.basicSalary").value(DEFAULT_BASIC_SALARY))
            .andExpect(jsonPath("$.allowance").value(DEFAULT_ALLOWANCE))
            .andExpect(jsonPath("$.ot").value(DEFAULT_OT))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.pf").value(DEFAULT_PF))
            .andExpect(jsonPath("$.extra").value(DEFAULT_EXTRA));
    }
    @Test
    @Transactional
    public void getNonExistingEmployeeSalary() throws Exception {
        // Get the employeeSalary
        restEmployeeSalaryMockMvc.perform(get("/api/employee-salaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeeSalary() throws Exception {
        // Initialize the database
        employeeSalaryRepository.saveAndFlush(employeeSalary);

        int databaseSizeBeforeUpdate = employeeSalaryRepository.findAll().size();

        // Update the employeeSalary
        EmployeeSalary updatedEmployeeSalary = employeeSalaryRepository.findById(employeeSalary.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeeSalary are not directly saved in db
        em.detach(updatedEmployeeSalary);
        updatedEmployeeSalary
            .basicSalary(UPDATED_BASIC_SALARY)
            .allowance(UPDATED_ALLOWANCE)
            .ot(UPDATED_OT)
            .bonus(UPDATED_BONUS)
            .description(UPDATED_DESCRIPTION)
            .tax(UPDATED_TAX)
            .pf(UPDATED_PF)
            .extra(UPDATED_EXTRA);
        EmployeeSalaryDTO employeeSalaryDTO = employeeSalaryMapper.toDto(updatedEmployeeSalary);

        restEmployeeSalaryMockMvc.perform(put("/api/employee-salaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeSalaryDTO)))
            .andExpect(status().isOk());

        // Validate the EmployeeSalary in the database
        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findAll();
        assertThat(employeeSalaryList).hasSize(databaseSizeBeforeUpdate);
        EmployeeSalary testEmployeeSalary = employeeSalaryList.get(employeeSalaryList.size() - 1);
        assertThat(testEmployeeSalary.getBasicSalary()).isEqualTo(UPDATED_BASIC_SALARY);
        assertThat(testEmployeeSalary.getAllowance()).isEqualTo(UPDATED_ALLOWANCE);
        assertThat(testEmployeeSalary.getOt()).isEqualTo(UPDATED_OT);
        assertThat(testEmployeeSalary.getBonus()).isEqualTo(UPDATED_BONUS);
        assertThat(testEmployeeSalary.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEmployeeSalary.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testEmployeeSalary.getPf()).isEqualTo(UPDATED_PF);
        assertThat(testEmployeeSalary.getExtra()).isEqualTo(UPDATED_EXTRA);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeeSalary() throws Exception {
        int databaseSizeBeforeUpdate = employeeSalaryRepository.findAll().size();

        // Create the EmployeeSalary
        EmployeeSalaryDTO employeeSalaryDTO = employeeSalaryMapper.toDto(employeeSalary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeSalaryMockMvc.perform(put("/api/employee-salaries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeeSalaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeSalary in the database
        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findAll();
        assertThat(employeeSalaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployeeSalary() throws Exception {
        // Initialize the database
        employeeSalaryRepository.saveAndFlush(employeeSalary);

        int databaseSizeBeforeDelete = employeeSalaryRepository.findAll().size();

        // Delete the employeeSalary
        restEmployeeSalaryMockMvc.perform(delete("/api/employee-salaries/{id}", employeeSalary.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmployeeSalary> employeeSalaryList = employeeSalaryRepository.findAll();
        assertThat(employeeSalaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
*/

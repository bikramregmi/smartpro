package com.rubic.smartpro.web.rest;

import com.rubic.smartpro.SmartproApp;
import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import com.rubic.smartpro.service.CompanyService;
import com.rubic.smartpro.service.dto.CompanyDTO;
import com.rubic.smartpro.service.mapper.CompanyMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CompanyResource} REST controller.
 */
@SpringBootTest(classes = SmartproApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompanyResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FY = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_BOOK_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BOOK_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CURRENCY_STRING = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_SYMBOL = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_SUB_STRING = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_SUB_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_DEALER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DEALER_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_FIELD = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRAFIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_EXTRAFIELD_1 = "BBBBBBBBBB";

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompanyMockMvc;

    private Company company;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Company createEntity(EntityManager em) {
        Company company = new Company()
            .companyName(DEFAULT_COMPANY_NAME)
            .address(DEFAULT_ADDRESS)
            .email(DEFAULT_EMAIL)
            .fy(DEFAULT_FY)
            .bookDate(DEFAULT_BOOK_DATE)
            .currencyString(DEFAULT_CURRENCY_STRING)
            .currencySymbol(DEFAULT_CURRENCY_SYMBOL)
            .currencySubString(DEFAULT_CURRENCY_SUB_STRING)
            .dealerType(DEFAULT_DEALER_TYPE)
            .taxRate(DEFAULT_TAX_RATE)
            .type(DEFAULT_TYPE)
            .extraField(DEFAULT_EXTRA_FIELD)
            .extrafield1(DEFAULT_EXTRAFIELD_1);
        return company;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Company createUpdatedEntity(EntityManager em) {
        Company company = new Company()
            .companyName(UPDATED_COMPANY_NAME)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .fy(UPDATED_FY)
            .bookDate(UPDATED_BOOK_DATE)
            .currencyString(UPDATED_CURRENCY_STRING)
            .currencySymbol(UPDATED_CURRENCY_SYMBOL)
            .currencySubString(UPDATED_CURRENCY_SUB_STRING)
            .dealerType(UPDATED_DEALER_TYPE)
            .taxRate(UPDATED_TAX_RATE)
            .type(UPDATED_TYPE)
            .extraField(UPDATED_EXTRA_FIELD)
            .extrafield1(UPDATED_EXTRAFIELD_1);
        return company;
    }

    @BeforeEach
    public void initTest() {
        company = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompany() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();
        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isCreated());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate + 1);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCompany.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCompany.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCompany.getFy()).isEqualTo(DEFAULT_FY);
        assertThat(testCompany.getBookDate()).isEqualTo(DEFAULT_BOOK_DATE);
        assertThat(testCompany.getCurrencyString()).isEqualTo(DEFAULT_CURRENCY_STRING);
        assertThat(testCompany.getCurrencySymbol()).isEqualTo(DEFAULT_CURRENCY_SYMBOL);
        assertThat(testCompany.getCurrencySubString()).isEqualTo(DEFAULT_CURRENCY_SUB_STRING);
        assertThat(testCompany.getDealerType()).isEqualTo(DEFAULT_DEALER_TYPE);
        assertThat(testCompany.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testCompany.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCompany.getExtraField()).isEqualTo(DEFAULT_EXTRA_FIELD);
        assertThat(testCompany.getExtrafield1()).isEqualTo(DEFAULT_EXTRAFIELD_1);
    }

    @Test
    @Transactional
    public void createCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyRepository.findAll().size();

        // Create the Company with an existing ID
        company.setId(1L);
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setCompanyName(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setAddress(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setEmail(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFyIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setFy(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBookDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setBookDate(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealerTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = companyRepository.findAll().size();
        // set the field null
        company.setDealerType(null);

        // Create the Company, which fails.
        CompanyDTO companyDTO = companyMapper.toDto(company);


        restCompanyMockMvc.perform(post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompanies() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get all the companyList
        restCompanyMockMvc.perform(get("/api/companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(company.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fy").value(hasItem(DEFAULT_FY.toString())))
            .andExpect(jsonPath("$.[*].bookDate").value(hasItem(DEFAULT_BOOK_DATE.toString())))
            .andExpect(jsonPath("$.[*].currencyString").value(hasItem(DEFAULT_CURRENCY_STRING)))
            .andExpect(jsonPath("$.[*].currencySymbol").value(hasItem(DEFAULT_CURRENCY_SYMBOL)))
            .andExpect(jsonPath("$.[*].currencySubString").value(hasItem(DEFAULT_CURRENCY_SUB_STRING)))
            .andExpect(jsonPath("$.[*].dealerType").value(hasItem(DEFAULT_DEALER_TYPE)))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].extraField").value(hasItem(DEFAULT_EXTRA_FIELD)))
            .andExpect(jsonPath("$.[*].extrafield1").value(hasItem(DEFAULT_EXTRAFIELD_1)));
    }
    
    @Test
    @Transactional
    public void getCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", company.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(company.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fy").value(DEFAULT_FY.toString()))
            .andExpect(jsonPath("$.bookDate").value(DEFAULT_BOOK_DATE.toString()))
            .andExpect(jsonPath("$.currencyString").value(DEFAULT_CURRENCY_STRING))
            .andExpect(jsonPath("$.currencySymbol").value(DEFAULT_CURRENCY_SYMBOL))
            .andExpect(jsonPath("$.currencySubString").value(DEFAULT_CURRENCY_SUB_STRING))
            .andExpect(jsonPath("$.dealerType").value(DEFAULT_DEALER_TYPE))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.extraField").value(DEFAULT_EXTRA_FIELD))
            .andExpect(jsonPath("$.extrafield1").value(DEFAULT_EXTRAFIELD_1));
    }
    @Test
    @Transactional
    public void getNonExistingCompany() throws Exception {
        // Get the company
        restCompanyMockMvc.perform(get("/api/companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Update the company
        Company updatedCompany = companyRepository.findById(company.getId()).get();
        // Disconnect from session so that the updates on updatedCompany are not directly saved in db
        em.detach(updatedCompany);
        updatedCompany
            .companyName(UPDATED_COMPANY_NAME)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .fy(UPDATED_FY)
            .bookDate(UPDATED_BOOK_DATE)
            .currencyString(UPDATED_CURRENCY_STRING)
            .currencySymbol(UPDATED_CURRENCY_SYMBOL)
            .currencySubString(UPDATED_CURRENCY_SUB_STRING)
            .dealerType(UPDATED_DEALER_TYPE)
            .taxRate(UPDATED_TAX_RATE)
            .type(UPDATED_TYPE)
            .extraField(UPDATED_EXTRA_FIELD)
            .extrafield1(UPDATED_EXTRAFIELD_1);
        CompanyDTO companyDTO = companyMapper.toDto(updatedCompany);

        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isOk());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
        Company testCompany = companyList.get(companyList.size() - 1);
        assertThat(testCompany.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCompany.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCompany.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCompany.getFy()).isEqualTo(UPDATED_FY);
        assertThat(testCompany.getBookDate()).isEqualTo(UPDATED_BOOK_DATE);
        assertThat(testCompany.getCurrencyString()).isEqualTo(UPDATED_CURRENCY_STRING);
        assertThat(testCompany.getCurrencySymbol()).isEqualTo(UPDATED_CURRENCY_SYMBOL);
        assertThat(testCompany.getCurrencySubString()).isEqualTo(UPDATED_CURRENCY_SUB_STRING);
        assertThat(testCompany.getDealerType()).isEqualTo(UPDATED_DEALER_TYPE);
        assertThat(testCompany.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testCompany.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCompany.getExtraField()).isEqualTo(UPDATED_EXTRA_FIELD);
        assertThat(testCompany.getExtrafield1()).isEqualTo(UPDATED_EXTRAFIELD_1);
    }

    @Test
    @Transactional
    public void updateNonExistingCompany() throws Exception {
        int databaseSizeBeforeUpdate = companyRepository.findAll().size();

        // Create the Company
        CompanyDTO companyDTO = companyMapper.toDto(company);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyMockMvc.perform(put("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(companyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Company in the database
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompany() throws Exception {
        // Initialize the database
        companyRepository.saveAndFlush(company);

        int databaseSizeBeforeDelete = companyRepository.findAll().size();

        // Delete the company
        restCompanyMockMvc.perform(delete("/api/companies/{id}", company.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Company> companyList = companyRepository.findAll();
        assertThat(companyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

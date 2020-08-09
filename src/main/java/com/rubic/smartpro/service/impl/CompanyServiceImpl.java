package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.service.CompanyService;
import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import com.rubic.smartpro.service.dto.CompanyDTO;
import com.rubic.smartpro.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        companyDTO.setFy(LocalDate.parse(companyDTO.getStrFy()));
        companyDTO.setBookDate(LocalDate.parse(companyDTO.getStrBookDate()));
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    /**
     * Get all the companies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable)
            .map(companyMapper::toDto);
    }


    /**
     * Get one company by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyDTO> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id)
            .map(companyMapper::toDto);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }

    @Override
    public String updateSelectedCompany(String id, String company) {
        List<Company> companyList=companyRepository.findAll();
        for(Company company1:companyList){
            company1.setExtraField("");
            companyRepository.save(company1);
        }
        Company company1=companyRepository.getOne(Long.valueOf(id));
        company1.setExtraField(company);
        companyRepository.save(company1);
        return company1.getExtraField();
    }

    @Override
    public Optional<CompanyDTO> findSelectedCompany(String status) {
        log.debug("Request to get Company : {}", status);
        return companyRepository.findSelectedCompanyByExtraField(status)
            .map(companyMapper::toDto);    }
}

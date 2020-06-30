package com.rubic.smartpro.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.rubic.smartpro.domain.PayrollGenerate;
import com.rubic.smartpro.domain.*; // for static metamodels
import com.rubic.smartpro.repository.PayrollGenerateRepository;
import com.rubic.smartpro.service.dto.PayrollGenerateCriteria;
import com.rubic.smartpro.service.dto.PayrollGenerateDTO;
import com.rubic.smartpro.service.mapper.PayrollGenerateMapper;

/**
 * Service for executing complex queries for {@link PayrollGenerate} entities in the database.
 * The main input is a {@link PayrollGenerateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PayrollGenerateDTO} or a {@link Page} of {@link PayrollGenerateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PayrollGenerateQueryService extends QueryService<PayrollGenerate> {

    private final Logger log = LoggerFactory.getLogger(PayrollGenerateQueryService.class);

    private final PayrollGenerateRepository payrollGenerateRepository;

    private final PayrollGenerateMapper payrollGenerateMapper;

    public PayrollGenerateQueryService(PayrollGenerateRepository payrollGenerateRepository, PayrollGenerateMapper payrollGenerateMapper) {
        this.payrollGenerateRepository = payrollGenerateRepository;
        this.payrollGenerateMapper = payrollGenerateMapper;
    }

    /**
     * Return a {@link List} of {@link PayrollGenerateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PayrollGenerateDTO> findByCriteria(PayrollGenerateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PayrollGenerate> specification = createSpecification(criteria);
        return payrollGenerateMapper.toDto(payrollGenerateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PayrollGenerateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PayrollGenerateDTO> findByCriteria(PayrollGenerateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PayrollGenerate> specification = createSpecification(criteria);
        return payrollGenerateRepository.findAll(specification, page)
            .map(payrollGenerateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PayrollGenerateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PayrollGenerate> specification = createSpecification(criteria);
        return payrollGenerateRepository.count(specification);
    }

    /**
     * Function to convert {@link PayrollGenerateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PayrollGenerate> createSpecification(PayrollGenerateCriteria criteria) {
        Specification<PayrollGenerate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PayrollGenerate_.id));
            }
            if (criteria.getSalaryMonth() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSalaryMonth(), PayrollGenerate_.salaryMonth));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PayrollGenerate_.description));
            }
            if (criteria.getTax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTax(), PayrollGenerate_.tax));
            }
            if (criteria.getPf() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPf(), PayrollGenerate_.pf));
            }
            if (criteria.getEmployeeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmployeeId(),
                    root -> root.join(PayrollGenerate_.employee, JoinType.LEFT).get(Employee_.id)));
            }
            if (criteria.getEmployeeSalaryId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmployeeSalaryId(),
                    root -> root.join(PayrollGenerate_.employeeSalary, JoinType.LEFT).get(EmployeeSalary_.id)));
            }
        }
        return specification;
    }
}

package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.domain.EmployeeSalary;
import com.rubic.smartpro.repository.EmployeeRepository;
import com.rubic.smartpro.repository.EmployeeSalaryRepository;
import com.rubic.smartpro.service.MailService;
import com.rubic.smartpro.service.PayrollGenerateService;
import com.rubic.smartpro.domain.PayrollGenerate;
import com.rubic.smartpro.repository.PayrollGenerateRepository;
import com.rubic.smartpro.service.dto.PayrollGenerateDTO;
import com.rubic.smartpro.service.mapper.PayrollGenerateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PayrollGenerate}.
 */
@Service
@Transactional
public class PayrollGenerateServiceImpl implements PayrollGenerateService {

    private final Logger log = LoggerFactory.getLogger(PayrollGenerateServiceImpl.class);

    private final PayrollGenerateRepository payrollGenerateRepository;

    private final PayrollGenerateMapper payrollGenerateMapper;

    private final EmployeeRepository employeeRepository;

    private final EmployeeSalaryRepository employeeSalaryRepository;

    private final MailService mailService;

    public PayrollGenerateServiceImpl(PayrollGenerateRepository payrollGenerateRepository, PayrollGenerateMapper payrollGenerateMapper, EmployeeRepository employeeRepository, EmployeeSalaryRepository employeeSalaryRepository, MailService mailService) {
        this.payrollGenerateRepository = payrollGenerateRepository;
        this.payrollGenerateMapper = payrollGenerateMapper;
        this.employeeRepository = employeeRepository;
        this.employeeSalaryRepository = employeeSalaryRepository;
        this.mailService = mailService;
    }

    /**
     * Save a payrollGenerate.
     *
     * @param payrollGenerateDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PayrollGenerateDTO save(PayrollGenerateDTO payrollGenerateDTO) {
        log.debug("Request to save PayrollGenerate : {}", payrollGenerateDTO);
        PayrollGenerate payrollGenerate = null;
        EmployeeSalary employeeSalary;
        List<Employee> employee;
        employee = employeeRepository.findAll();
        for (Employee employee1 : employee) {
            employeeSalary = employeeSalaryRepository.getOne(employee1.getEmployeeSalary().getId());
            payrollGenerateDTO.setEmployee(Collections.singleton(employee1));
            payrollGenerateDTO.setEmployeeName(employee1.getFullName());
            payrollGenerateDTO.setDescription("Salary Statement");
            long totalSalary =employeeSalary.getBasicSalary() + employeeSalary.getAllowance() + employeeSalary.getBonus() + employeeSalary.getOt();
            payrollGenerateDTO.setSalaryTotal(String.valueOf(totalSalary));
            payrollGenerate = payrollGenerateMapper.toEntity(payrollGenerateDTO);
            payrollGenerate = payrollGenerateRepository.save(payrollGenerate);
        }

        try {
            mailService.sendPayroll(employee, payrollGenerateDTO);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return payrollGenerateMapper.toDto(payrollGenerate);
    }

    /**
     * Get all the payrollGenerates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PayrollGenerateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PayrollGenerates");
        return payrollGenerateRepository.findAll(pageable)
            .map(payrollGenerateMapper::toDto);
    }


    /**
     * Get one payrollGenerate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PayrollGenerateDTO> findOne(Long id) {
        log.debug("Request to get PayrollGenerate : {}", id);
        return payrollGenerateRepository.findById(id)
            .map(payrollGenerateMapper::toDto);
    }

    /**
     * Delete the payrollGenerate by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PayrollGenerate : {}", id);
        payrollGenerateRepository.deleteById(id);
    }
}

package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.repository.EmployeeRepository;
import com.rubic.smartpro.service.EmployeeSalaryService;
import com.rubic.smartpro.domain.EmployeeSalary;
import com.rubic.smartpro.repository.EmployeeSalaryRepository;
import com.rubic.smartpro.service.dto.EmployeeSalaryDTO;
import com.rubic.smartpro.service.mapper.EmployeeSalaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EmployeeSalary}.
 */
@Service
@Transactional
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService {

    private final Logger log = LoggerFactory.getLogger(EmployeeSalaryServiceImpl.class);

    private final EmployeeSalaryRepository employeeSalaryRepository;

    private final EmployeeSalaryMapper employeeSalaryMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeSalaryServiceImpl(EmployeeSalaryRepository employeeSalaryRepository, EmployeeSalaryMapper employeeSalaryMapper, EmployeeRepository employeeRepository) {
        this.employeeSalaryRepository = employeeSalaryRepository;
        this.employeeSalaryMapper = employeeSalaryMapper;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save a employeeSalary.
     *
     * @param employeeSalaryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeSalaryDTO save(EmployeeSalaryDTO employeeSalaryDTO) {
        log.debug("Request to save EmployeeSalary : {}", employeeSalaryDTO);
        Optional<EmployeeSalary> employeeSalary1;
        Optional<Employee> employee;
        if (employeeSalaryDTO.getId() != null) {
            employeeSalary1 = employeeSalaryRepository.findById(employeeSalaryDTO.getId());
            employeeSalary1.get().setAllowance(employeeSalaryDTO.getAllowance());
            employeeSalary1.get().setBasicSalary(employeeSalaryDTO.getBasicSalary());
            employeeSalary1.get().setBonus(employeeSalaryDTO.getBonus());
            employeeSalary1.get().setDescription(employeeSalaryDTO.getDescription());
            employeeSalary1.get().setExtra(employeeSalaryDTO.getExtra());
            employeeSalary1.get().setOt(employeeSalaryDTO.getOt());
            employeeSalary1.get().setPf(employeeSalaryDTO.getPf());
            employeeSalary1.get().setTax(employeeSalaryDTO.getTax());
            return employeeSalaryMapper.toDto(employeeSalaryRepository.save(employeeSalary1.get()));
        }
        EmployeeSalary employeeSalary = employeeSalaryMapper.toEntity(employeeSalaryDTO);
        employeeSalary = employeeSalaryRepository.save(employeeSalary);
        if (employeeSalaryDTO.getEmployeeId() != null) {
            employee = employeeRepository.findById(Long.valueOf(employeeSalaryDTO.getEmployeeId()));
            employee.get().setEmployeeSalary(employeeSalary);
            employeeRepository.save(employee.get());
        }
        return employeeSalaryMapper.toDto(employeeSalary);
    }

    /**
     * Get all the employeeSalaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeSalaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeeSalaries");
        return employeeSalaryRepository.findAll(pageable)
                .map(employeeSalaryMapper::toDto);
    }


    /**
     * Get one employeeSalary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeSalaryDTO> findOne(Long id) {
        log.debug("Request to get EmployeeSalary : {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
            return employeeSalaryRepository.findById(employee.get().getEmployeeSalary().getId())
                    .map(employeeSalaryMapper::toDto);
    }

    /**
     * Delete the employeeSalary by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeeSalary : {}", id);
        employeeSalaryRepository.deleteById(id);
    }
}

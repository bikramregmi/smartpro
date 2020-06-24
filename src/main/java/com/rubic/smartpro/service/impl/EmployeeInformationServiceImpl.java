package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.repository.EmployeeRepository;
import com.rubic.smartpro.service.EmployeeInformationService;
import com.rubic.smartpro.domain.EmployeeInformation;
import com.rubic.smartpro.repository.EmployeeInformationRepository;
import com.rubic.smartpro.service.dto.EmployeeInformationDTO;
import com.rubic.smartpro.service.mapper.EmployeeInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service Implementation for managing {@link EmployeeInformation}.
 */
@Service
@Transactional
public class EmployeeInformationServiceImpl implements EmployeeInformationService {

    private final Logger log = LoggerFactory.getLogger(EmployeeInformationServiceImpl.class);

    private final EmployeeInformationRepository employeeInformationRepository;

    private final EmployeeInformationMapper employeeInformationMapper;

    private final EmployeeRepository employeeRepository;

    public EmployeeInformationServiceImpl(EmployeeInformationRepository employeeInformationRepository, EmployeeInformationMapper employeeInformationMapper, EmployeeRepository employeeRepository) {
        this.employeeInformationRepository = employeeInformationRepository;
        this.employeeInformationMapper = employeeInformationMapper;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save a employeeInformation.
     *
     * @param employeeInformationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeInformationDTO save(EmployeeInformationDTO employeeInformationDTO) {
        Optional<Employee> employee;
        log.debug("Request to save EmployeeInformation : {}", employeeInformationDTO);
        Optional<EmployeeInformation> employeeInformation1;
       if(employeeInformationDTO.getId()!=null){
           employeeInformation1=employeeInformationRepository.findById(employeeInformationDTO.getId());
           employeeInformation1.get().setMobileNumber(employeeInformationDTO.getMobileNumber());
           employeeInformation1.get().setPhoneNumber(employeeInformationDTO.getPhoneNumber());
           employeeInformation1.get().setParentName(employeeInformationDTO.getParentName());
           employeeInformation1.get().setPanNumber(employeeInformationDTO.getPanNumber());
           employeeInformation1.get().setJoiningDate(employeeInformationDTO.getJoiningDate());
           employeeInformation1.get().setIsMarried(employeeInformationDTO.isIsMarried());
           employeeInformation1.get().setGender(employeeInformationDTO.getGender());
           employeeInformation1.get().setEmergencyContactNumber(employeeInformationDTO.getEmergencyContactNumber());
           employeeInformation1.get().setExtraField(employeeInformationDTO.getExtraField());
           employeeInformation1.get().setDocumentNumber(employeeInformationDTO.getDocumentNumber());
           employeeInformation1.get().setDesignation(employeeInformationDTO.getDesignation());
           employeeInformation1.get().setDob(employeeInformationDTO.getDob());
           employeeInformation1.get().setAddressline1(employeeInformationDTO.getAddressline1());
           employeeInformation1.get().setAddressline2(employeeInformationDTO.getAddressline2());
           employeeInformation1.get().setBloodGroup(employeeInformationDTO.getBloodGroup());
           return employeeInformationMapper.toDto(employeeInformationRepository.save(employeeInformation1.get()));
       }
        EmployeeInformation employeeInformation = employeeInformationMapper.toEntity(employeeInformationDTO);
        employeeInformation = employeeInformationRepository.save(employeeInformation);
        if(employeeInformationDTO.getEmployeeId()!=null) {
            employee = employeeRepository.findById(Long.valueOf(employeeInformationDTO.getEmployeeId()));
            employee.get().setEmployeeInformation(employeeInformation);
            employeeRepository.save(employee.get());
        }
        return employeeInformationMapper.toDto(employeeInformation);
    }

    /**
     * Get all the employeeInformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeInformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeeInformations");
        return employeeInformationRepository.findAll(pageable)
            .map(employeeInformationMapper::toDto);
    }


    /**
     * Get one employeeInformation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeInformationDTO> findOne(Long id) {
        log.debug("Request to get EmployeeInformation : {}", id);
        return employeeInformationRepository.findById(id)
            .map(employeeInformationMapper::toDto);
    }

    /**
     * Delete the employeeInformation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeeInformation : {}", id);
        employeeInformationRepository.deleteById(id);
    }
}

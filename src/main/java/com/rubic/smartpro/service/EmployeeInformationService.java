package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.EmployeeInformationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.EmployeeInformation}.
 */
public interface EmployeeInformationService {

    /**
     * Save a employeeInformation.
     *
     * @param employeeInformationDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeInformationDTO save(EmployeeInformationDTO employeeInformationDTO);

    /**
     * Get all the employeeInformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeInformationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" employeeInformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeInformationDTO> findOne(Long id);

    /**
     * Delete the "id" employeeInformation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.rubic.smartpro.service;

import com.rubic.smartpro.service.dto.AttendanceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.rubic.smartpro.domain.Attendance}.
 */
public interface AttendanceService {

    /**
     * Save a attendance.
     *
     * @param attendanceDTO the entity to save.
     * @return the persisted entity.
     */
    AttendanceDTO save(AttendanceDTO attendanceDTO);

    /**
     * Get all the attendances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AttendanceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" attendance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AttendanceDTO> findOne(Long id);

    /**
     * Delete the "id" attendance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<AttendanceDTO> findAllForToday(Pageable pageable);

    boolean checkAlreadyRegistered( AttendanceDTO attendanceDTO);

    AttendanceDTO findOneForToday(Long id);
}

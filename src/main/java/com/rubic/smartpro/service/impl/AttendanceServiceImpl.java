package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.repository.EmployeeRepository;
import com.rubic.smartpro.service.AttendanceService;
import com.rubic.smartpro.domain.Attendance;
import com.rubic.smartpro.repository.AttendanceRepository;
import com.rubic.smartpro.service.dto.AttendanceDTO;
import com.rubic.smartpro.service.mapper.AttendanceMapper;
import com.rubic.smartpro.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Attendance}.
 */
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final Logger log = LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private final AttendanceRepository attendanceRepository;

    private final AttendanceMapper attendanceMapper;

    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Save a attendance.
     *
     * @param attendanceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AttendanceDTO save(AttendanceDTO attendanceDTO) {
        log.debug("Request to save Attendance : {}", attendanceDTO);
        Attendance attendance;
        Employee employee= employeeRepository.findByeCode(attendanceDTO.getEmployeeCode());
        if(employee!=null) {
            attendanceDTO.setEmployeeId(employee.getId());
            if(attendanceDTO.getCheckOutDate()!=null) {
                attendance= attendanceRepository.getOne(attendanceDTO.getId());
                attendance.setCheckOut(Instant.parse(attendanceDTO.getCheckOutDate()));
            } else {
                attendanceDTO.setCheckIn(Instant.parse(attendanceDTO.getCheckInDate()));
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDate strDate = LocalDate.parse(formatter.format(date));
                attendanceDTO.setInsertedDate(strDate);
                attendance = attendanceMapper.toEntity(attendanceDTO);
            }
            attendance = attendanceRepository.save(attendance);
            return attendanceMapper.toDto(attendance);
        }
        else
            throw new BadRequestAlertException("Employee is not available", "Attendance", "idexists");
    }

    /**
     * Get all the attendances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Attendances");
        return attendanceRepository.findAll(pageable)
            .map(attendanceMapper::toDto);
    }

    /**
     * Get all the attendances for today.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttendanceDTO> findAllForToday(Pageable pageable) {
        log.debug("Request to get all Attendances");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate strDate= LocalDate.parse(formatter.format(date));
        return attendanceMapper.toDto(attendanceRepository.findAllForToday(strDate));
    }

    @Override
    public boolean checkAlreadyRegistered(@Valid AttendanceDTO attendanceDTO) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate strDate= LocalDate.parse(formatter.format(date));
        Attendance attendance=attendanceRepository.findByInsertedDateAndEmployee_eCode(strDate,attendanceDTO.getEmployeeCode());
        if(attendanceDTO.getCheckOutDate()!=null){
           if(attendance.getCheckOut()==null)
               return false;
        }
        return attendance != null;
    }

    @Override
    public AttendanceDTO findOneForToday(Long id) {
        log.debug("Request to get all Attendances");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate strDate= LocalDate.parse(formatter.format(date));
        return attendanceMapper.toDto(attendanceRepository.findOneByIdAndInsertedDate(id,strDate));
    }


    /**
     * Get one attendance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttendanceDTO> findOne(Long id) {
        log.debug("Request to get Attendance : {}", id);
        return attendanceRepository.findById(id)
            .map(attendanceMapper::toDto);
    }

    /**
     * Delete the attendance by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attendance : {}", id);
        attendanceRepository.deleteById(id);
    }
}

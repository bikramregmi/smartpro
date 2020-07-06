package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.AttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attendance} and its DTO {@link AttendanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface AttendanceMapper extends EntityMapper<AttendanceDTO, Attendance> {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.fullName", target = "employeeFullName")
    @Mapping(source = "checkIn",target="checkInDate")
    @Mapping(source = "employee.eCode",target="employeeCode")
    AttendanceDTO toDto(Attendance attendance);

    @Mapping(source = "employeeId", target = "employee")
    Attendance toEntity(AttendanceDTO attendanceDTO);

    default Attendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attendance attendance = new Attendance();
        attendance.setId(id);
        return attendance;
    }
}

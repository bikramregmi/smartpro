package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeSalaryMapper.class, EmployeeInformationMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "employeeSalary.id", target = "employeeSalaryId")
    @Mapping(source = "employeeSalary.description", target = "employeeSalaryDescription")
    @Mapping(source = "employeeInformation.id", target = "employeeInformationId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "employeeSalaryId", target = "employeeSalary")
    @Mapping(source = "employeeInformationId", target = "employeeInformation")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}

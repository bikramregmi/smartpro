package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.EmployeeSalaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeeSalary} and its DTO {@link EmployeeSalaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeSalaryMapper extends EntityMapper<EmployeeSalaryDTO, EmployeeSalary> {



    default EmployeeSalary fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setId(id);
        return employeeSalary;
    }
}

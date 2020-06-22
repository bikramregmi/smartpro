package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.EmployeeInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeeInformation} and its DTO {@link EmployeeInformationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeeInformationMapper extends EntityMapper<EmployeeInformationDTO, EmployeeInformation> {



    default EmployeeInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmployeeInformation employeeInformation = new EmployeeInformation();
        employeeInformation.setId(id);
        return employeeInformation;
    }
}

package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.PayrollGenerateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PayrollGenerate} and its DTO {@link PayrollGenerateDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, EmployeeSalaryMapper.class})
public interface PayrollGenerateMapper extends EntityMapper<PayrollGenerateDTO, PayrollGenerate> {


    default PayrollGenerate fromId(Long id) {
        if (id == null) {
            return null;
        }
        PayrollGenerate payrollGenerate = new PayrollGenerate();
        payrollGenerate.setId(id);
        return payrollGenerate;
    }
}

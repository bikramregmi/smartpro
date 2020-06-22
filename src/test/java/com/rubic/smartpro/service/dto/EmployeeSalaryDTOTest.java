package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class EmployeeSalaryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeSalaryDTO.class);
        EmployeeSalaryDTO employeeSalaryDTO1 = new EmployeeSalaryDTO();
        employeeSalaryDTO1.setId(1L);
        EmployeeSalaryDTO employeeSalaryDTO2 = new EmployeeSalaryDTO();
        assertThat(employeeSalaryDTO1).isNotEqualTo(employeeSalaryDTO2);
        employeeSalaryDTO2.setId(employeeSalaryDTO1.getId());
        assertThat(employeeSalaryDTO1).isEqualTo(employeeSalaryDTO2);
        employeeSalaryDTO2.setId(2L);
        assertThat(employeeSalaryDTO1).isNotEqualTo(employeeSalaryDTO2);
        employeeSalaryDTO1.setId(null);
        assertThat(employeeSalaryDTO1).isNotEqualTo(employeeSalaryDTO2);
    }
}

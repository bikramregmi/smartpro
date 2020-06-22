package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class EmployeeInformationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeInformationDTO.class);
        EmployeeInformationDTO employeeInformationDTO1 = new EmployeeInformationDTO();
        employeeInformationDTO1.setId(1L);
        EmployeeInformationDTO employeeInformationDTO2 = new EmployeeInformationDTO();
        assertThat(employeeInformationDTO1).isNotEqualTo(employeeInformationDTO2);
        employeeInformationDTO2.setId(employeeInformationDTO1.getId());
        assertThat(employeeInformationDTO1).isEqualTo(employeeInformationDTO2);
        employeeInformationDTO2.setId(2L);
        assertThat(employeeInformationDTO1).isNotEqualTo(employeeInformationDTO2);
        employeeInformationDTO1.setId(null);
        assertThat(employeeInformationDTO1).isNotEqualTo(employeeInformationDTO2);
    }
}

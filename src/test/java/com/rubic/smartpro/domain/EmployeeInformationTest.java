package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class EmployeeInformationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeInformation.class);
        EmployeeInformation employeeInformation1 = new EmployeeInformation();
        employeeInformation1.setId(1L);
        EmployeeInformation employeeInformation2 = new EmployeeInformation();
        employeeInformation2.setId(employeeInformation1.getId());
        assertThat(employeeInformation1).isEqualTo(employeeInformation2);
        employeeInformation2.setId(2L);
        assertThat(employeeInformation1).isNotEqualTo(employeeInformation2);
        employeeInformation1.setId(null);
        assertThat(employeeInformation1).isNotEqualTo(employeeInformation2);
    }
}

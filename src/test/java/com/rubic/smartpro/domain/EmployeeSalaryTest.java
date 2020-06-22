package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class EmployeeSalaryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeSalary.class);
        EmployeeSalary employeeSalary1 = new EmployeeSalary();
        employeeSalary1.setId(1L);
        EmployeeSalary employeeSalary2 = new EmployeeSalary();
        employeeSalary2.setId(employeeSalary1.getId());
        assertThat(employeeSalary1).isEqualTo(employeeSalary2);
        employeeSalary2.setId(2L);
        assertThat(employeeSalary1).isNotEqualTo(employeeSalary2);
        employeeSalary1.setId(null);
        assertThat(employeeSalary1).isNotEqualTo(employeeSalary2);
    }
}

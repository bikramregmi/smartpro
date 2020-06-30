package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class PayrollGenerateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayrollGenerate.class);
        PayrollGenerate payrollGenerate1 = new PayrollGenerate();
        payrollGenerate1.setId(1L);
        PayrollGenerate payrollGenerate2 = new PayrollGenerate();
        payrollGenerate2.setId(payrollGenerate1.getId());
        assertThat(payrollGenerate1).isEqualTo(payrollGenerate2);
        payrollGenerate2.setId(2L);
        assertThat(payrollGenerate1).isNotEqualTo(payrollGenerate2);
        payrollGenerate1.setId(null);
        assertThat(payrollGenerate1).isNotEqualTo(payrollGenerate2);
    }
}

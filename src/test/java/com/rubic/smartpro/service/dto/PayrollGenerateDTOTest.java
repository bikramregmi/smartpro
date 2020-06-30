package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class PayrollGenerateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayrollGenerateDTO.class);
        PayrollGenerateDTO payrollGenerateDTO1 = new PayrollGenerateDTO();
        payrollGenerateDTO1.setId(1L);
        PayrollGenerateDTO payrollGenerateDTO2 = new PayrollGenerateDTO();
        assertThat(payrollGenerateDTO1).isNotEqualTo(payrollGenerateDTO2);
        payrollGenerateDTO2.setId(payrollGenerateDTO1.getId());
        assertThat(payrollGenerateDTO1).isEqualTo(payrollGenerateDTO2);
        payrollGenerateDTO2.setId(2L);
        assertThat(payrollGenerateDTO1).isNotEqualTo(payrollGenerateDTO2);
        payrollGenerateDTO1.setId(null);
        assertThat(payrollGenerateDTO1).isNotEqualTo(payrollGenerateDTO2);
    }
}

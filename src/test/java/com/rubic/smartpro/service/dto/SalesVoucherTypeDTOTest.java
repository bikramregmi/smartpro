package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class SalesVoucherTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesVoucherTypeDTO.class);
        SalesVoucherTypeDTO salesVoucherTypeDTO1 = new SalesVoucherTypeDTO();
        salesVoucherTypeDTO1.setId(1L);
        SalesVoucherTypeDTO salesVoucherTypeDTO2 = new SalesVoucherTypeDTO();
        assertThat(salesVoucherTypeDTO1).isNotEqualTo(salesVoucherTypeDTO2);
        salesVoucherTypeDTO2.setId(salesVoucherTypeDTO1.getId());
        assertThat(salesVoucherTypeDTO1).isEqualTo(salesVoucherTypeDTO2);
        salesVoucherTypeDTO2.setId(2L);
        assertThat(salesVoucherTypeDTO1).isNotEqualTo(salesVoucherTypeDTO2);
        salesVoucherTypeDTO1.setId(null);
        assertThat(salesVoucherTypeDTO1).isNotEqualTo(salesVoucherTypeDTO2);
    }
}

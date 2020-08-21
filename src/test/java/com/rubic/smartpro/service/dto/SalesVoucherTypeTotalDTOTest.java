package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class SalesVoucherTypeTotalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesVoucherTypeTotalDTO.class);
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO1 = new SalesVoucherTypeTotalDTO();
        salesVoucherTypeTotalDTO1.setId(1L);
        SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO2 = new SalesVoucherTypeTotalDTO();
        assertThat(salesVoucherTypeTotalDTO1).isNotEqualTo(salesVoucherTypeTotalDTO2);
        salesVoucherTypeTotalDTO2.setId(salesVoucherTypeTotalDTO1.getId());
        assertThat(salesVoucherTypeTotalDTO1).isEqualTo(salesVoucherTypeTotalDTO2);
        salesVoucherTypeTotalDTO2.setId(2L);
        assertThat(salesVoucherTypeTotalDTO1).isNotEqualTo(salesVoucherTypeTotalDTO2);
        salesVoucherTypeTotalDTO1.setId(null);
        assertThat(salesVoucherTypeTotalDTO1).isNotEqualTo(salesVoucherTypeTotalDTO2);
    }
}

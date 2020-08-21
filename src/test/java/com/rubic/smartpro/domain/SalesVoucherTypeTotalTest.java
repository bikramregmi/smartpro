package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class SalesVoucherTypeTotalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesVoucherTypeTotal.class);
        SalesVoucherTypeTotal salesVoucherTypeTotal1 = new SalesVoucherTypeTotal();
        salesVoucherTypeTotal1.setId(1L);
        SalesVoucherTypeTotal salesVoucherTypeTotal2 = new SalesVoucherTypeTotal();
        salesVoucherTypeTotal2.setId(salesVoucherTypeTotal1.getId());
        assertThat(salesVoucherTypeTotal1).isEqualTo(salesVoucherTypeTotal2);
        salesVoucherTypeTotal2.setId(2L);
        assertThat(salesVoucherTypeTotal1).isNotEqualTo(salesVoucherTypeTotal2);
        salesVoucherTypeTotal1.setId(null);
        assertThat(salesVoucherTypeTotal1).isNotEqualTo(salesVoucherTypeTotal2);
    }
}

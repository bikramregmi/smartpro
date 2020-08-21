package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class SalesVoucherTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesVoucherType.class);
        SalesVoucherType salesVoucherType1 = new SalesVoucherType();
        salesVoucherType1.setId(1L);
        SalesVoucherType salesVoucherType2 = new SalesVoucherType();
        salesVoucherType2.setId(salesVoucherType1.getId());
        assertThat(salesVoucherType1).isEqualTo(salesVoucherType2);
        salesVoucherType2.setId(2L);
        assertThat(salesVoucherType1).isNotEqualTo(salesVoucherType2);
        salesVoucherType1.setId(null);
        assertThat(salesVoucherType1).isNotEqualTo(salesVoucherType2);
    }
}

package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class VoucherTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoucherType.class);
        VoucherType voucherType1 = new VoucherType();
        voucherType1.setId(1L);
        VoucherType voucherType2 = new VoucherType();
        voucherType2.setId(voucherType1.getId());
        assertThat(voucherType1).isEqualTo(voucherType2);
        voucherType2.setId(2L);
        assertThat(voucherType1).isNotEqualTo(voucherType2);
        voucherType1.setId(null);
        assertThat(voucherType1).isNotEqualTo(voucherType2);
    }
}

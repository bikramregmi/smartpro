package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class AccountingVoucherTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountingVoucher.class);
        AccountingVoucher accountingVoucher1 = new AccountingVoucher();
        accountingVoucher1.setId(1L);
        AccountingVoucher accountingVoucher2 = new AccountingVoucher();
        accountingVoucher2.setId(accountingVoucher1.getId());
        assertThat(accountingVoucher1).isEqualTo(accountingVoucher2);
        accountingVoucher2.setId(2L);
        assertThat(accountingVoucher1).isNotEqualTo(accountingVoucher2);
        accountingVoucher1.setId(null);
        assertThat(accountingVoucher1).isNotEqualTo(accountingVoucher2);
    }
}

package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class AccountingVoucherDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountingVoucherDTO.class);
        AccountingVoucherDTO accountingVoucherDTO1 = new AccountingVoucherDTO();
        accountingVoucherDTO1.setId(1L);
        AccountingVoucherDTO accountingVoucherDTO2 = new AccountingVoucherDTO();
        assertThat(accountingVoucherDTO1).isNotEqualTo(accountingVoucherDTO2);
        accountingVoucherDTO2.setId(accountingVoucherDTO1.getId());
        assertThat(accountingVoucherDTO1).isEqualTo(accountingVoucherDTO2);
        accountingVoucherDTO2.setId(2L);
        assertThat(accountingVoucherDTO1).isNotEqualTo(accountingVoucherDTO2);
        accountingVoucherDTO1.setId(null);
        assertThat(accountingVoucherDTO1).isNotEqualTo(accountingVoucherDTO2);
    }
}

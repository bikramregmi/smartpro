package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class VoucherTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VoucherTypeDTO.class);
        VoucherTypeDTO voucherTypeDTO1 = new VoucherTypeDTO();
        voucherTypeDTO1.setId(1L);
        VoucherTypeDTO voucherTypeDTO2 = new VoucherTypeDTO();
        assertThat(voucherTypeDTO1).isNotEqualTo(voucherTypeDTO2);
        voucherTypeDTO2.setId(voucherTypeDTO1.getId());
        assertThat(voucherTypeDTO1).isEqualTo(voucherTypeDTO2);
        voucherTypeDTO2.setId(2L);
        assertThat(voucherTypeDTO1).isNotEqualTo(voucherTypeDTO2);
        voucherTypeDTO1.setId(null);
        assertThat(voucherTypeDTO1).isNotEqualTo(voucherTypeDTO2);
    }
}

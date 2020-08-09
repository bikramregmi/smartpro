package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class LedgerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LedgerDTO.class);
        LedgerDTO ledgerDTO1 = new LedgerDTO();
        ledgerDTO1.setId(1L);
        LedgerDTO ledgerDTO2 = new LedgerDTO();
        assertThat(ledgerDTO1).isNotEqualTo(ledgerDTO2);
        ledgerDTO2.setId(ledgerDTO1.getId());
        assertThat(ledgerDTO1).isEqualTo(ledgerDTO2);
        ledgerDTO2.setId(2L);
        assertThat(ledgerDTO1).isNotEqualTo(ledgerDTO2);
        ledgerDTO1.setId(null);
        assertThat(ledgerDTO1).isNotEqualTo(ledgerDTO2);
    }
}

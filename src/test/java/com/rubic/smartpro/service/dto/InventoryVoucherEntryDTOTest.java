package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class InventoryVoucherEntryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventoryVoucherEntryDTO.class);
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO1 = new InventoryVoucherEntryDTO();
        inventoryVoucherEntryDTO1.setId(1L);
        InventoryVoucherEntryDTO inventoryVoucherEntryDTO2 = new InventoryVoucherEntryDTO();
        assertThat(inventoryVoucherEntryDTO1).isNotEqualTo(inventoryVoucherEntryDTO2);
        inventoryVoucherEntryDTO2.setId(inventoryVoucherEntryDTO1.getId());
        assertThat(inventoryVoucherEntryDTO1).isEqualTo(inventoryVoucherEntryDTO2);
        inventoryVoucherEntryDTO2.setId(2L);
        assertThat(inventoryVoucherEntryDTO1).isNotEqualTo(inventoryVoucherEntryDTO2);
        inventoryVoucherEntryDTO1.setId(null);
        assertThat(inventoryVoucherEntryDTO1).isNotEqualTo(inventoryVoucherEntryDTO2);
    }
}

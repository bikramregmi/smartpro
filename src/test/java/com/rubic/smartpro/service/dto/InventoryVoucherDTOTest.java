package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class InventoryVoucherDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventoryVoucherDTO.class);
        InventoryVoucherDTO inventoryVoucherDTO1 = new InventoryVoucherDTO();
        inventoryVoucherDTO1.setId(1L);
        InventoryVoucherDTO inventoryVoucherDTO2 = new InventoryVoucherDTO();
        assertThat(inventoryVoucherDTO1).isNotEqualTo(inventoryVoucherDTO2);
        inventoryVoucherDTO2.setId(inventoryVoucherDTO1.getId());
        assertThat(inventoryVoucherDTO1).isEqualTo(inventoryVoucherDTO2);
        inventoryVoucherDTO2.setId(2L);
        assertThat(inventoryVoucherDTO1).isNotEqualTo(inventoryVoucherDTO2);
        inventoryVoucherDTO1.setId(null);
        assertThat(inventoryVoucherDTO1).isNotEqualTo(inventoryVoucherDTO2);
    }
}

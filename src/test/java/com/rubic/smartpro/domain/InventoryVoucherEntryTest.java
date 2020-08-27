package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class InventoryVoucherEntryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventoryVoucherEntry.class);
        InventoryVoucherEntry inventoryVoucherEntry1 = new InventoryVoucherEntry();
        inventoryVoucherEntry1.setId(1L);
        InventoryVoucherEntry inventoryVoucherEntry2 = new InventoryVoucherEntry();
        inventoryVoucherEntry2.setId(inventoryVoucherEntry1.getId());
        assertThat(inventoryVoucherEntry1).isEqualTo(inventoryVoucherEntry2);
        inventoryVoucherEntry2.setId(2L);
        assertThat(inventoryVoucherEntry1).isNotEqualTo(inventoryVoucherEntry2);
        inventoryVoucherEntry1.setId(null);
        assertThat(inventoryVoucherEntry1).isNotEqualTo(inventoryVoucherEntry2);
    }
}

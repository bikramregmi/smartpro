package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class InventoryVoucherTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InventoryVoucher.class);
        InventoryVoucher inventoryVoucher1 = new InventoryVoucher();
        inventoryVoucher1.setId(1L);
        InventoryVoucher inventoryVoucher2 = new InventoryVoucher();
        inventoryVoucher2.setId(inventoryVoucher1.getId());
        assertThat(inventoryVoucher1).isEqualTo(inventoryVoucher2);
        inventoryVoucher2.setId(2L);
        assertThat(inventoryVoucher1).isNotEqualTo(inventoryVoucher2);
        inventoryVoucher1.setId(null);
        assertThat(inventoryVoucher1).isNotEqualTo(inventoryVoucher2);
    }
}

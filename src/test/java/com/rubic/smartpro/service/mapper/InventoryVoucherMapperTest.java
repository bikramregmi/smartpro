package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryVoucherMapperTest {

    private InventoryVoucherMapper inventoryVoucherMapper;

    @BeforeEach
    public void setUp() {
        inventoryVoucherMapper = new InventoryVoucherMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inventoryVoucherMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inventoryVoucherMapper.fromId(null)).isNull();
    }
}

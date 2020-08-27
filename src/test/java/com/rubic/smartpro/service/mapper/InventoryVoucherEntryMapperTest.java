package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InventoryVoucherEntryMapperTest {

    private InventoryVoucherEntryMapper inventoryVoucherEntryMapper;

    @BeforeEach
    public void setUp() {
        inventoryVoucherEntryMapper = new InventoryVoucherEntryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(inventoryVoucherEntryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(inventoryVoucherEntryMapper.fromId(null)).isNull();
    }
}

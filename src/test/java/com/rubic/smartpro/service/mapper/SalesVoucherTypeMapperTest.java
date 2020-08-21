package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesVoucherTypeMapperTest {

    private SalesVoucherTypeMapper salesVoucherTypeMapper;

    @BeforeEach
    public void setUp() {
        salesVoucherTypeMapper = new SalesVoucherTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(salesVoucherTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(salesVoucherTypeMapper.fromId(null)).isNull();
    }
}

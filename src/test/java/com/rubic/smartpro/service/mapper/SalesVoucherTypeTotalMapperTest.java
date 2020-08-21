package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesVoucherTypeTotalMapperTest {

    private SalesVoucherTypeTotalMapper salesVoucherTypeTotalMapper;

    @BeforeEach
    public void setUp() {
        salesVoucherTypeTotalMapper = new SalesVoucherTypeTotalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(salesVoucherTypeTotalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(salesVoucherTypeTotalMapper.fromId(null)).isNull();
    }
}

package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VoucherTypeMapperTest {

    private VoucherTypeMapper voucherTypeMapper;

    @BeforeEach
    public void setUp() {
        voucherTypeMapper = new VoucherTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(voucherTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(voucherTypeMapper.fromId(null)).isNull();
    }
}

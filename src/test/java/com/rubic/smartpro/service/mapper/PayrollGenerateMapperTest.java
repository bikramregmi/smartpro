package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PayrollGenerateMapperTest {

    private PayrollGenerateMapper payrollGenerateMapper;

    @BeforeEach
    public void setUp() {
        payrollGenerateMapper = new PayrollGenerateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(payrollGenerateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(payrollGenerateMapper.fromId(null)).isNull();
    }
}

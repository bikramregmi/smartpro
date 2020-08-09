package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LedgerMapperTest {

    private LedgerMapper ledgerMapper;

    @BeforeEach
    public void setUp() {
        ledgerMapper = new LedgerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ledgerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ledgerMapper.fromId(null)).isNull();
    }
}

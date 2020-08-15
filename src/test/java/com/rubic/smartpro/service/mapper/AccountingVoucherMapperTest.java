package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountingVoucherMapperTest {

    private AccountingVoucherMapper accountingVoucherMapper;

    @BeforeEach
    public void setUp() {
        accountingVoucherMapper = new AccountingVoucherMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountingVoucherMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountingVoucherMapper.fromId(null)).isNull();
    }
}

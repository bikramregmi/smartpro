package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeSalaryMapperTest {

    private EmployeeSalaryMapper employeeSalaryMapper;

    @BeforeEach
    public void setUp() {
        employeeSalaryMapper = new EmployeeSalaryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(employeeSalaryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(employeeSalaryMapper.fromId(null)).isNull();
    }
}

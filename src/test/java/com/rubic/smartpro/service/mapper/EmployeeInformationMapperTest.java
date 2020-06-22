package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeInformationMapperTest {

    private EmployeeInformationMapper employeeInformationMapper;

    @BeforeEach
    public void setUp() {
        employeeInformationMapper = new EmployeeInformationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(employeeInformationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(employeeInformationMapper.fromId(null)).isNull();
    }
}

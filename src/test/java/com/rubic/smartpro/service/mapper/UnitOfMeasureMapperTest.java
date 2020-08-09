package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitOfMeasureMapperTest {

    private UnitOfMeasureMapper unitOfMeasureMapper;

    @BeforeEach
    public void setUp() {
        unitOfMeasureMapper = new UnitOfMeasureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(unitOfMeasureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(unitOfMeasureMapper.fromId(null)).isNull();
    }
}

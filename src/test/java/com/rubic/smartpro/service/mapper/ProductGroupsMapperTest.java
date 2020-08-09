package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductGroupsMapperTest {

    private ProductGroupsMapper productGroupsMapper;

    @BeforeEach
    public void setUp() {
        productGroupsMapper = new ProductGroupsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productGroupsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productGroupsMapper.fromId(null)).isNull();
    }
}

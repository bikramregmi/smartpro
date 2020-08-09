package com.rubic.smartpro.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsMapperTest {

    private GroupsMapper groupsMapper;

    @BeforeEach
    public void setUp() {
        groupsMapper = new GroupsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(groupsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(groupsMapper.fromId(null)).isNull();
    }
}

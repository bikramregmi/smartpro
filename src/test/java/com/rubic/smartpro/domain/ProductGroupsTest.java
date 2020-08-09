package com.rubic.smartpro.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class ProductGroupsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductGroups.class);
        ProductGroups productGroups1 = new ProductGroups();
        productGroups1.setId(1L);
        ProductGroups productGroups2 = new ProductGroups();
        productGroups2.setId(productGroups1.getId());
        assertThat(productGroups1).isEqualTo(productGroups2);
        productGroups2.setId(2L);
        assertThat(productGroups1).isNotEqualTo(productGroups2);
        productGroups1.setId(null);
        assertThat(productGroups1).isNotEqualTo(productGroups2);
    }
}

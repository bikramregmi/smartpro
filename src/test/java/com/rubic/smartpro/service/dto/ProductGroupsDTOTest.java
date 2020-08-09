package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class ProductGroupsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductGroupsDTO.class);
        ProductGroupsDTO productGroupsDTO1 = new ProductGroupsDTO();
        productGroupsDTO1.setId(1L);
        ProductGroupsDTO productGroupsDTO2 = new ProductGroupsDTO();
        assertThat(productGroupsDTO1).isNotEqualTo(productGroupsDTO2);
        productGroupsDTO2.setId(productGroupsDTO1.getId());
        assertThat(productGroupsDTO1).isEqualTo(productGroupsDTO2);
        productGroupsDTO2.setId(2L);
        assertThat(productGroupsDTO1).isNotEqualTo(productGroupsDTO2);
        productGroupsDTO1.setId(null);
        assertThat(productGroupsDTO1).isNotEqualTo(productGroupsDTO2);
    }
}

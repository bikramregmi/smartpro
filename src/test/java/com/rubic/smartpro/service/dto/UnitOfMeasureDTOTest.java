package com.rubic.smartpro.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rubic.smartpro.web.rest.TestUtil;

public class UnitOfMeasureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitOfMeasureDTO.class);
        UnitOfMeasureDTO unitOfMeasureDTO1 = new UnitOfMeasureDTO();
        unitOfMeasureDTO1.setId(1L);
        UnitOfMeasureDTO unitOfMeasureDTO2 = new UnitOfMeasureDTO();
        assertThat(unitOfMeasureDTO1).isNotEqualTo(unitOfMeasureDTO2);
        unitOfMeasureDTO2.setId(unitOfMeasureDTO1.getId());
        assertThat(unitOfMeasureDTO1).isEqualTo(unitOfMeasureDTO2);
        unitOfMeasureDTO2.setId(2L);
        assertThat(unitOfMeasureDTO1).isNotEqualTo(unitOfMeasureDTO2);
        unitOfMeasureDTO1.setId(null);
        assertThat(unitOfMeasureDTO1).isNotEqualTo(unitOfMeasureDTO2);
    }
}

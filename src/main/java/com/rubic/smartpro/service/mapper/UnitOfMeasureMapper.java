package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.UnitOfMeasureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnitOfMeasure} and its DTO {@link UnitOfMeasureDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UnitOfMeasureMapper extends EntityMapper<UnitOfMeasureDTO, UnitOfMeasure> {



    default UnitOfMeasure fromId(Long id) {
        if (id == null) {
            return null;
        }
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(id);
        return unitOfMeasure;
    }
}

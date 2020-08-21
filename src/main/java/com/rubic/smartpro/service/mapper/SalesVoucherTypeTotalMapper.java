package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.SalesVoucherTypeTotalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesVoucherTypeTotal} and its DTO {@link SalesVoucherTypeTotalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SalesVoucherTypeTotalMapper extends EntityMapper<SalesVoucherTypeTotalDTO, SalesVoucherTypeTotal> {


    @Mapping(target = "salesVoucherTypes", ignore = true)
    @Mapping(target = "removeSalesVoucherType", ignore = true)
    SalesVoucherTypeTotal toEntity(SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO);

    default SalesVoucherTypeTotal fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesVoucherTypeTotal salesVoucherTypeTotal = new SalesVoucherTypeTotal();
        salesVoucherTypeTotal.setId(id);
        return salesVoucherTypeTotal;
    }
}

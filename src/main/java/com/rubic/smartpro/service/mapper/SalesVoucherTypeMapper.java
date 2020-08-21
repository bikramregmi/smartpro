package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.SalesVoucherTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesVoucherType} and its DTO {@link SalesVoucherTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {AccountingVoucherMapper.class})
public interface SalesVoucherTypeMapper extends EntityMapper<SalesVoucherTypeDTO, SalesVoucherType> {

    @Mapping(source = "salesVoucherTypeTotal", target = "salesVoucherTypeTotalDTO")
    @Mapping(source = "accountingVoucher.id", target = "accountingVoucherId")
    SalesVoucherTypeDTO toDto(SalesVoucherType salesVoucherType);

    @Mapping(source = "accountingVoucherId", target = "accountingVoucher")
    SalesVoucherType toEntity(SalesVoucherTypeDTO salesVoucherTypeDTO);

    default SalesVoucherType fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesVoucherType salesVoucherType = new SalesVoucherType();
        salesVoucherType.setId(id);
        return salesVoucherType;
    }
}

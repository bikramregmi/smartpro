package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.VoucherTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VoucherType} and its DTO {@link VoucherTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface VoucherTypeMapper extends EntityMapper<VoucherTypeDTO, VoucherType> {

    @Mapping(source = "company.id", target = "companyId")
    VoucherTypeDTO toDto(VoucherType voucherType);

    @Mapping(source = "companyId", target = "company")
    VoucherType toEntity(VoucherTypeDTO voucherTypeDTO);

    default VoucherType fromId(Long id) {
        if (id == null) {
            return null;
        }
        VoucherType voucherType = new VoucherType();
        voucherType.setId(id);
        return voucherType;
    }
}

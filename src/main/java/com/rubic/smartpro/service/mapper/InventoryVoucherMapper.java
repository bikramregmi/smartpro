package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.InventoryVoucherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InventoryVoucher} and its DTO {@link InventoryVoucherDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InventoryVoucherMapper extends EntityMapper<InventoryVoucherDTO, InventoryVoucher> {



    default InventoryVoucher fromId(Long id) {
        if (id == null) {
            return null;
        }
        InventoryVoucher inventoryVoucher = new InventoryVoucher();
        inventoryVoucher.setId(id);
        return inventoryVoucher;
    }
}

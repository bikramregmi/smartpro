package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.InventoryVoucherEntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InventoryVoucherEntry} and its DTO {@link InventoryVoucherEntryDTO}.
 */
@Mapper(componentModel = "spring", uses = {InventoryVoucherMapper.class})
public interface InventoryVoucherEntryMapper extends EntityMapper<InventoryVoucherEntryDTO, InventoryVoucherEntry> {

    InventoryVoucherEntryDTO toDto(InventoryVoucherEntry inventoryVoucherEntry);

    InventoryVoucherEntry toEntity(InventoryVoucherEntryDTO inventoryVoucherEntryDTO);

    default InventoryVoucherEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        InventoryVoucherEntry inventoryVoucherEntry = new InventoryVoucherEntry();
        inventoryVoucherEntry.setId(id);
        return inventoryVoucherEntry;
    }
}

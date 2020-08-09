package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.LedgerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ledger} and its DTO {@link LedgerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LedgerMapper extends EntityMapper<LedgerDTO, Ledger> {

//    @Mapping(source = "ledger.id", target = "ledgerId")
    LedgerDTO toDto(Ledger ledger);

//    @Mapping(target = "groups", ignore = true)
//    @Mapping(target = "removeGroups", ignore = true)
//    @Mapping(source = "ledgerId", target = "ledger")
    Ledger toEntity(LedgerDTO ledgerDTO);

    default Ledger fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ledger ledger = new Ledger();
        ledger.setId(id);
        return ledger;
    }
}

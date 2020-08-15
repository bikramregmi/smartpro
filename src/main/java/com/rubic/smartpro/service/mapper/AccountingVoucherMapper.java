package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.AccountingVoucherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountingVoucher} and its DTO {@link AccountingVoucherDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountingVoucherMapper extends EntityMapper<AccountingVoucherDTO, AccountingVoucher> {



    default AccountingVoucher fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountingVoucher accountingVoucher = new AccountingVoucher();
        accountingVoucher.setId(id);
        return accountingVoucher;
    }
}

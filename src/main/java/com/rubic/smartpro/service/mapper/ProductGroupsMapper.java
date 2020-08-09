package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.ProductGroupsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductGroups} and its DTO {@link ProductGroupsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface ProductGroupsMapper extends EntityMapper<ProductGroupsDTO, ProductGroups> {

    @Mapping(source = "company.id", target = "companyId")
    ProductGroupsDTO toDto(ProductGroups productGroups);

    @Mapping(source = "companyId", target = "company")
    ProductGroups toEntity(ProductGroupsDTO productGroupsDTO);

    default ProductGroups fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductGroups productGroups = new ProductGroups();
        productGroups.setId(id);
        return productGroups;
    }
}

package com.rubic.smartpro.service.mapper;


import com.rubic.smartpro.domain.*;
import com.rubic.smartpro.service.dto.ProductItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductItem} and its DTO {@link ProductItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface ProductItemMapper extends EntityMapper<ProductItemDTO, ProductItem> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "productGroups.name", target = "group")
    ProductItemDTO toDto(ProductItem productItem);

    @Mapping(source = "companyId", target = "company")
    ProductItem toEntity(ProductItemDTO productItemDTO);

    default ProductItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductItem productItem = new ProductItem();
        productItem.setId(id);
        return productItem;
    }
}

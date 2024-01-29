package com.eshop.eshop.mapper.product;

import com.eshop.eshop.dto.product.ProductVariantDto;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductVariantMapper {

    private final ModelMapper mapper;

    public ProductVariant convertToEntity(ProductVariantDto productVariantDto) {
        return mapper.map(productVariantDto, ProductVariant.class);
    }

    public ProductVariantDto convertToDto(ProductVariant productVariant) {
        return mapper.map(productVariant, ProductVariantDto.class);
    }
}

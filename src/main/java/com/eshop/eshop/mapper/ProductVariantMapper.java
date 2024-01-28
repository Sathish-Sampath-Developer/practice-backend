package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.dto.ProductVariantDto;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductVariantMapper {

    private ModelMapper mapper;

    public ProductVariant convertToEntity(ProductVariantDto productVariantDto) {
        return mapper.map(productVariantDto, ProductVariant.class);
    }

    public ProductVariantDto convertToDto(ProductVariant productVariant) {
        return mapper.map(productVariant, ProductVariantDto.class);
    }
}

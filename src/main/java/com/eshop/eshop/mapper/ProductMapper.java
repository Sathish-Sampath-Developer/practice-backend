package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final ModelMapper mapper;

    public ProductEntity convertToEntity(ProductDto productDto) {
        return mapper.map(productDto, ProductEntity.class);
    }

    public ProductDto convertToDto(ProductEntity product) {
        return mapper.map(product, ProductDto.class);
    }
}

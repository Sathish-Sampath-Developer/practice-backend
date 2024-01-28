package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ShoppingCartItemDto;
import com.eshop.eshop.entity.ShoppingCartItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartItemMapper {

    private final ModelMapper mapper;

    public ShoppingCartItemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ShoppingCartItemEntity convertToEntity(ShoppingCartItemDto shoppingCartItemDto) {
        return mapper.map(shoppingCartItemDto, ShoppingCartItemEntity.class);
    }

    public ShoppingCartItemDto convertToDto(ShoppingCartItemEntity merchantStore){
        return mapper.map(merchantStore,ShoppingCartItemDto.class);
    }
}

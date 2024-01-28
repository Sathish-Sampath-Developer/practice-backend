package com.eshop.eshop.service;

import com.eshop.eshop.entity.ShoppingCartItemEntity;

import java.util.List;

public interface ShoppingCartItemService {
    ShoppingCartItemEntity addCartItem(ShoppingCartItemEntity shoppingCartItem);
    List<ShoppingCartItemEntity> getAllCartItemByIds(List<Long> ids);
    ShoppingCartItemEntity updateCartItem(Long id,ShoppingCartItemEntity shoppingCartItem);
    void  removeCartItem(Long id);
}

package com.eshop.eshop.service;

import com.eshop.eshop.entity.ShoppingCartEntity;
import com.eshop.eshop.entity.ShoppingCartItemEntity;

public interface ShoppingCartService {
    ShoppingCartEntity saveOrUpdate(ShoppingCartItemEntity item);

    ShoppingCartEntity getShoppingCart(Long customerId);

    ShoppingCartItemEntity populateShoppingCartItem(ShoppingCartItemEntity item,ShoppingCartEntity shoppingCart);

    void removeShoppingCart(Long id);
}

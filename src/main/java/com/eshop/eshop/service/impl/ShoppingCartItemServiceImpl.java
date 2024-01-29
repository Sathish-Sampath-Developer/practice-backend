package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.ShoppingCartItemEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.ShoppingCartItemRepository;
import com.eshop.eshop.service.ShoppingCartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public ShoppingCartItemEntity addCartItem(ShoppingCartItemEntity shoppingCartItem) {
        return shoppingCartItemRepository.save(shoppingCartItem);
    }

    @Override
    public List<ShoppingCartItemEntity> getAllCartItemByIds(List<Long> ids) {
        return shoppingCartItemRepository.findAllByIdIn(ids).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Shopping item is empty"));
    }

    @Override
    public ShoppingCartItemEntity updateCartItem(Long id, ShoppingCartItemEntity shoppingCartItem) {
        ShoppingCartItemEntity shoppingCartItemToUpdate = shoppingCartItemRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Shopping Cart item is does not exists in given id!."));

        shoppingCartItemToUpdate.setItemPrice(shoppingCartItem.getItemPrice());
        shoppingCartItemToUpdate.setSku(shoppingCartItem.getSku());
        shoppingCartItemToUpdate.setQuantity(shoppingCartItem.getQuantity());
        shoppingCartItemToUpdate.setProductId(shoppingCartItem.getProductId());

        return shoppingCartItemRepository.save(shoppingCartItemToUpdate);
    }

    @Override
    public void removeCartItem(Long id) {
        ShoppingCartItemEntity shoppingCartItemEntity = shoppingCartItemRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Shopping Cart item is does not exists in given id!."));
        shoppingCartItemRepository.delete(shoppingCartItemEntity);
    }
}

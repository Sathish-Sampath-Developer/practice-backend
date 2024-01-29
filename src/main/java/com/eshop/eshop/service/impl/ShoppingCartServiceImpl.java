package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.ShoppingCartEntity;
import com.eshop.eshop.entity.ShoppingCartItemEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.ShoppingCartItemRepository;
import com.eshop.eshop.repository.ShoppingCartRepository;
import com.eshop.eshop.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public ShoppingCartEntity saveOrUpdate(ShoppingCartItemEntity cartItem) {


        ShoppingCartEntity existingShoppingCart = shoppingCartRepository.findByCustomerId(8L).orElse(null);

        if (existingShoppingCart != null) {
            ShoppingCartItemEntity item = populateShoppingCartItem(cartItem, existingShoppingCart);

            existingShoppingCart.getLineItems().add(item);

            return shoppingCartRepository.save(existingShoppingCart);
        }

        ShoppingCartEntity newShoppingCart = new ShoppingCartEntity();


        return shoppingCartRepository.save(newShoppingCart);
    }

    @Override
    public ShoppingCartItemEntity populateShoppingCartItem(ShoppingCartItemEntity item, ShoppingCartEntity shoppingCart) throws ServiceException {

        item.setItemPrice(item.getItemPrice());
        item.setProductId(item.getProductId());
        item.setSku(item.getSku());
        item.setQuantity(item.getQuantity());

        return shoppingCartItemRepository.save(item);

    }


    @Override
    public ShoppingCartEntity getShoppingCart(Long customerId) {
        return shoppingCartRepository.findByCustomerId(customerId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Shopping cart is empty!."));
    }

    @Override
    public void removeShoppingCart(Long id) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Shopping Cart is does not exists in given id!."));
        shoppingCartEntity.setDeleted(true);
        shoppingCartRepository.save(shoppingCartEntity);
    }
}

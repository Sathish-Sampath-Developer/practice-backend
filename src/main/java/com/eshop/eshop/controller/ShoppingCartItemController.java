package com.eshop.eshop.controller;

import com.eshop.eshop.entity.ShoppingCartItemEntity;
import com.eshop.eshop.service.ShoppingCartItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/shopping-cart-item")
@Tag(name = "Shopping Cart Items Controller", description = "This url for handle Shopping Cart Items")
@AllArgsConstructor
public class ShoppingCartItemController {

    private ShoppingCartItemService shoppingCartItemService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<ShoppingCartItemEntity> addToCartItem(@RequestBody ShoppingCartItemEntity shoppingCartItem) {
        return new ResponseEntity<>(shoppingCartItemService.addCartItem(shoppingCartItem), HttpStatus.CREATED);
    }

    @GetMapping("/get-cart-items")
    public ResponseEntity<List<ShoppingCartItemEntity>> getAllCartItemByIds(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(shoppingCartItemService.getAllCartItemByIds(ids), HttpStatus.OK);
    }

    @PutMapping("/update-cart-items/{id}")
    public ResponseEntity<ShoppingCartItemEntity> getAllCartItemByIds(@RequestBody ShoppingCartItemEntity shoppingCartItemEntity, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(shoppingCartItemService.updateCartItem(id, shoppingCartItemEntity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-cart-items/{id}")
    public ResponseEntity<Void> deleteCartItems(@PathVariable(name = "id") Long id) {
        shoppingCartItemService.removeCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

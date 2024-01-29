package com.eshop.eshop.controller;

import com.eshop.eshop.entity.ShoppingCartEntity;
import com.eshop.eshop.entity.ShoppingCartItemEntity;
import com.eshop.eshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/private/shopping-cart/")
@Tag(name = "Shopping Cart Controller", description = "This url for handle Shopping Cart")
@AllArgsConstructor
public class ShoppingCartController {


    private ShoppingCartService shoppingCartService;

    @PostMapping("add-to-cart")
    public ResponseEntity<ShoppingCartEntity> addToCart(@RequestBody ShoppingCartItemEntity item) {
        return new ResponseEntity<>(shoppingCartService.saveOrUpdate(item), HttpStatus.ACCEPTED);
    }

    @GetMapping("get-cart/{customerId}")
    public ResponseEntity<ShoppingCartEntity> getCart(@PathVariable(name = "customerId") Long customerId) {
        return new ResponseEntity<>(shoppingCartService.getShoppingCart(customerId), HttpStatus.OK);
    }

    @DeleteMapping("remove-cart/{customerId}")
    public ResponseEntity<Void> removeCart(@PathVariable(name = "customerId") Long customerId) {
        shoppingCartService.getShoppingCart(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

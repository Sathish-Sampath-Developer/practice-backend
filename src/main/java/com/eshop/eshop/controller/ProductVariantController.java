package com.eshop.eshop.controller;

import com.eshop.eshop.dto.ProductVariantDto;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import com.eshop.eshop.service.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products/{productId}/variants")
@AllArgsConstructor
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    @PostMapping
    private ResponseEntity<ProductVariantDto> addProductVariant(@PathVariable(name = ("productId")) Long productId, @RequestBody ProductVariantDto productVariant) {
        return new ResponseEntity<>(productVariantService.addVariantToProduct(productId, productVariant), HttpStatus.CREATED);
    }

}

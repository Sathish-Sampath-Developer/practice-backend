package com.eshop.eshop.controller;

import com.eshop.eshop.dto.product.ProductVariantDto;
import com.eshop.eshop.service.ProductVariantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products/{productId}/variants")
@AllArgsConstructor
@Tag(name = "Product Variant Controller", description = "This url for CRUD of Products variant")
public class ProductVariantController {

    private ProductVariantService productVariantService;

    @PostMapping
    private ResponseEntity<ProductVariantDto> addProductVariant(@PathVariable(name = ("productId")) Long productId, @RequestBody ProductVariantDto productVariant) {
        return new ResponseEntity<>(productVariantService.addVariantToProduct(productId, productVariant), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<ProductVariantDto>> getListOfVariantByProductId(@PathVariable(name = ("productId")) Long productId) {
        return new ResponseEntity<>(productVariantService.getListOfVariantByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/{variantId}")
    private ResponseEntity<ProductVariantDto> getListOfVariantByProductIdAndVariantId(@PathVariable(name = ("productId")) Long productId, @PathVariable(name = ("variantId")) Long variantId) {
        return new ResponseEntity<>(productVariantService.getListOfVariantByProductIdAndVariantId(productId, variantId), HttpStatus.OK);
    }

    @PutMapping("/{variantId}")
    private ResponseEntity<ProductVariantDto> updateProductVariant(@PathVariable(name = ("productId")) Long productId, @PathVariable(name = ("variantId")) Long variantId, @RequestBody ProductVariantDto productVariantDto) {
        return new ResponseEntity<>(productVariantService.updateProductVariant(productId, variantId, productVariantDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{variantId}")
    private ResponseEntity<Void> deleteProductVariant(@PathVariable(name = ("productId")) Long productId, @PathVariable(name = ("variantId")) Long variantId) {

        productVariantService.deleteProductVariant(productId, variantId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

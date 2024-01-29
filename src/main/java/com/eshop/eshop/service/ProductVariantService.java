package com.eshop.eshop.service;

import com.eshop.eshop.dto.product.ProductVariantDto;

import java.util.List;

public interface ProductVariantService {
    ProductVariantDto addVariantToProduct(Long id, ProductVariantDto productVariantDto);

    List<ProductVariantDto> getListOfVariantByProductId(Long id);

    ProductVariantDto getListOfVariantByProductIdAndVariantId(Long productId,Long productVariantId);

    ProductVariantDto updateProductVariant(Long productId,Long productVariantId,ProductVariantDto productVariant);
    void deleteProductVariant(Long productId,Long productVariantId);
}

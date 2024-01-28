package com.eshop.eshop.service;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.entity.product.variant.ProductVariant;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    List<ProductDto> getProductList();
    ProductDto getProductById(long id);
    ProductDto updateProduct(long  id, ProductDto product);
    void deleteProduct(long id);

}

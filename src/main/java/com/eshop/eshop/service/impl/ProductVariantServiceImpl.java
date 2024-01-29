package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.product.ProductVariantDto;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.product.ProductVariantMapper;
import com.eshop.eshop.repository.ProductRepository;
import com.eshop.eshop.repository.ProductVariantRepository;
import com.eshop.eshop.service.AuthService;
import com.eshop.eshop.service.ProductVariantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private ProductRepository productRepository;
    private ProductVariantRepository productVariantRepository;
    private AuthService authService;
    private ProductVariantMapper productVariantMapper;

    @Override
    public ProductVariantDto addVariantToProduct(Long id, ProductVariantDto productVariantDto) {

        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found in given id!."));

        ProductVariant productVariant = productVariantMapper.convertToEntity(productVariantDto);

        productVariant.setProduct(product);
        productVariant.setCreatedBy(authService.getAuthenticatedUsername());

        return productVariantMapper.convertToDto(productVariantRepository.save(productVariant));
    }

    @Override
    public List<ProductVariantDto> getListOfVariantByProductId(Long id) {
        List<ProductVariant> listOfProductVariant = productVariantRepository.findByProductId(id);
        return listOfProductVariant.stream().map((productVariant -> productVariantMapper.convertToDto(productVariant))).toList();
    }

    @Override
    public ProductVariantDto getListOfVariantByProductIdAndVariantId(Long productId, Long productVariantId) {

        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!productVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }

        return productVariantMapper.convertToDto(productVariant);
    }

    @Override
    public ProductVariantDto updateProductVariant(Long productId, Long productVariantId, ProductVariantDto productVariant) {

        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ProductVariant updateProductVariant = productVariantRepository.findById(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!updateProductVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }

        updateProductVariant.setQuantity(productVariant.getQuantity());
        updateProductVariant.setSize(productVariant.getSize());
        updateProductVariant.setColor(productVariant.getColor());
        updateProductVariant.setDateAvailable(productVariant.getDateAvailable());
        updateProductVariant.setImage(productVariant.getImage());
        updateProductVariant.setModifiedBy(authService.getAuthenticatedUsername());

        return productVariantMapper.convertToDto(productVariantRepository.save(updateProductVariant));
    }

    @Override
    public void deleteProductVariant(Long productId, Long productVariantId) {

        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!productVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }

        productVariantRepository.delete(productVariant);
    }
}

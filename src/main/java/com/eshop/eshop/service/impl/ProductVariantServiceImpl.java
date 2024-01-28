package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ProductVariantDto;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.ProductVariantMapper;
import com.eshop.eshop.repository.ProductRepository;
import com.eshop.eshop.repository.ProductVariantRepository;
import com.eshop.eshop.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    private ProductVariantMapper productVariantMapper;

    @Override
    public ProductVariantDto addVariantToProduct(Long id, ProductVariantDto productVariantDto) {

        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found in given id!."));

        ProductVariant productVariant = productVariantMapper.convertToEntity(productVariantDto);

        productVariant.setProduct(product);

        return productVariantMapper.convertToDto(productVariantRepository.save(productVariant));
    }

    @Override
    public List<ProductVariantDto> getListOfVariantByProductId(Long id) {
        List<ProductVariant> listOfProductVariant = productVariantRepository.findByProductIdAndDeletedFalse(id);
        return listOfProductVariant.stream().map((productVariant -> productVariantMapper.convertToDto(productVariant))).toList();
    }

    @Override
    public ProductVariantDto getListOfVariantByProductIdAndVariantId(Long productId, Long productVariantId) {

        ProductEntity product = productRepository.findByIdAndDeletedFalse(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ProductVariant productVariant = productVariantRepository.findByIdAndDeletedFalse(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!productVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }

        return productVariantMapper.convertToDto(productVariant);
    }

    @Override
    public ProductVariantDto updateProductVariant(Long productId, Long productVariantId, ProductVariantDto productVariant) {

        ProductEntity product = productRepository.findByIdAndDeletedFalse(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ProductVariant updateProductVariant = productVariantRepository.findByIdAndDeletedFalse(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!updateProductVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }

        updateProductVariant.setQuantity(productVariant.getQuantity());
        updateProductVariant.setSize(productVariant.getSize());
        updateProductVariant.setColor(productVariant.getColor());
        updateProductVariant.setDateAvailable(productVariant.getDateAvailable());
        updateProductVariant.setImage(productVariant.getImage());

        return productVariantMapper.convertToDto(productVariantRepository.save(updateProductVariant));
    }

    @Override
    public void deleteProductVariant(Long productId, Long productVariantId) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(productId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        product.setDeleted(true);

        ProductVariant productVariant = productVariantRepository.findByIdAndDeletedFalse(productVariantId).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product variant was not found given id!."));

        if (!productVariant.getProduct().getId().equals(product.getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "Product variant doesn't belongs to product!.");
        }
        productVariant.setDeleted(true);
        productVariantRepository.save(productVariant);
    }
}

package com.eshop.eshop.mapper.product;

import com.eshop.eshop.dto.product.ProductDto;
import com.eshop.eshop.dto.product.ProductDtoWithoutMerchantAndManufacturer;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.mapper.manufacturer.ManufacturerMapper;
import com.eshop.eshop.mapper.merchant.MerchantMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final ProductVariantMapper productVariantMapper;

    @Autowired
    private final MerchantMapper merchantMapper;

    @Autowired
    private final ManufacturerMapper manufacturerMapper;


    public ProductEntity convertToEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDto.getId());
        productEntity.setTitle(productDto.getTitle());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setSku(productDto.getSku());
        productEntity.setThumbnail(productDto.getThumbnail());
        productEntity.setLength(productDto.getLength());
        productEntity.setWidth(productDto.getWidth());
        productEntity.setHeight(productDto.getHeight());
        productEntity.setWeight(productDto.getWeight());
        productEntity.setProductQuantity(productDto.getProductQuantity());
        productEntity.setProductDateAvailable(productDto.getProductDateAvailable());
        productEntity.setRegion(productDto.getRegion());
        productEntity.setAvailable(productDto.getAvailable());
        productEntity.setProductQuantityOrderMin(productDto.getProductQuantityOrderMin());
        productEntity.setProductQuantityOrderMax(productDto.getProductQuantityOrderMax());
        productEntity.setCollection(productDto.getCollection());
        productEntity.setSeUrl(productDto.getSeUrl());
        productEntity.setMetaTagTitle(productDto.getMetaTagTitle());
        productEntity.setMetaTagKeywords(productDto.getMetaTagKeywords());
        productEntity.setMetaTagDescription(productDto.getMetaTagDescription());

        if (productDto.getVariants() != null) {
            productEntity.setVariants(productDto.getVariants().stream().map(productVariantMapper::convertToEntity).collect(Collectors.toSet()));
        }

        productEntity.setManufacturer(manufacturerMapper.convertToEntity(productDto.getManufacturer()));
        productEntity.setMerchantStore(merchantMapper.convertToEntity(productDto.getMerchantStore()));
//        productEntity.setDeleted(productDto.isDeleted());
        productEntity.setDateCreated(productDto.getDateCreated());
        productEntity.setDateModified(productDto.getDateModified());
        productEntity.setCreatedBy(productDto.getCreatedBy());
        productEntity.setModifiedBy(productDto.getModifiedBy());
        return productEntity;
    }

    public ProductDto convertToDto(ProductEntity product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setSku(product.getSku());
        productDto.setThumbnail(product.getThumbnail());
        productDto.setLength(product.getLength());
        productDto.setWidth(product.getWidth());
        productDto.setHeight(product.getHeight());
        productDto.setWeight(product.getWeight());
        productDto.setProductQuantity(product.getProductQuantity());
        productDto.setProductDateAvailable(product.getProductDateAvailable());
        productDto.setRegion(product.getRegion());
        productDto.setAvailable(product.getAvailable());
        productDto.setProductQuantityOrderMin(product.getProductQuantityOrderMin());
        productDto.setProductQuantityOrderMax(product.getProductQuantityOrderMax());
        productDto.setCollection(product.getCollection());
        productDto.setSeUrl(product.getSeUrl());
        productDto.setMetaTagTitle(product.getMetaTagTitle());
        productDto.setMetaTagKeywords(product.getMetaTagKeywords());
        productDto.setMetaTagDescription(product.getMetaTagDescription());

        if (product.getVariants() != null) {
            productDto.setVariants(product.getVariants().stream().map(productVariantMapper::convertToDto).collect(Collectors.toSet()));
        }

        productDto.setManufacturer(manufacturerMapper.convertToDto(product.getManufacturer()));
        productDto.setMerchantStore(merchantMapper.convertToDto(product.getMerchantStore()));
//        productDto.setDeleted(product.isDeleted());
        productDto.setDateCreated(product.getDateCreated());
        productDto.setDateModified(product.getDateModified());
        productDto.setCreatedBy(product.getCreatedBy());
        productDto.setModifiedBy(product.getModifiedBy());

        return productDto;
    }

    public ProductDtoWithoutMerchantAndManufacturer convertToDtoWithoutMerchantAndManufacturer(ProductEntity product) {

        ProductDtoWithoutMerchantAndManufacturer productDto = new ProductDtoWithoutMerchantAndManufacturer();

        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setSku(product.getSku());
        productDto.setThumbnail(product.getThumbnail());
        productDto.setLength(product.getLength());
        productDto.setWidth(product.getWidth());
        productDto.setHeight(product.getHeight());
        productDto.setWeight(product.getWeight());
        productDto.setProductQuantity(product.getProductQuantity());
        productDto.setProductDateAvailable(product.getProductDateAvailable());
        productDto.setRegion(product.getRegion());
        productDto.setAvailable(product.getAvailable());
        productDto.setProductQuantityOrderMin(product.getProductQuantityOrderMin());
        productDto.setProductQuantityOrderMax(product.getProductQuantityOrderMax());
        productDto.setCollection(product.getCollection());
        productDto.setSeUrl(product.getSeUrl());
        productDto.setMetaTagTitle(product.getMetaTagTitle());
        productDto.setMetaTagKeywords(product.getMetaTagKeywords());
        productDto.setMetaTagDescription(product.getMetaTagDescription());

        if (product.getVariants() != null) {
            productDto.setVariants(product.getVariants().stream().map(productVariantMapper::convertToDto).collect(Collectors.toSet()));
        }
//        productDto.setDeleted(product.isDeleted());
        productDto.setDateCreated(product.getDateCreated());
        productDto.setDateModified(product.getDateModified());
        productDto.setCreatedBy(product.getCreatedBy());
        productDto.setModifiedBy(product.getModifiedBy());

        return productDto;
    }
}

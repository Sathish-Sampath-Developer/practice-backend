package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private ProductVariantMapper productVariantMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductEntity convertToEntity(ProductDto productDto) {
        return mapper.map(productDto, ProductEntity.class);
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

        if (product.getVariants() != null){
            productDto.setVariants(product.getVariants().stream().map(productVariant -> productVariantMapper.convertToDto(productVariant)).collect(Collectors.toSet()));
        }

        productDto.setManufacturer(manufacturerMapper.convertToDto(product.getManufacturer()));
        productDto.setMerchantStore(merchantMapper.convertToDto(product.getMerchantStore()));
        productDto.setDeleted(product.isDeleted());
        productDto.setDateCreated(product.getDateCreated());
        productDto.setDateModified(product.getDateModified());
        productDto.setCreatedBy(product.getCreatedBy());
        productDto.setModifiedBy(product.getModifiedBy());

        return productDto;
    }
}

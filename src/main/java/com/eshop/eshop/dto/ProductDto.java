package com.eshop.eshop.dto;

import com.eshop.eshop.constants.SchemaConstant;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.entity.MerchantStoreEntity;
import com.eshop.eshop.entity.product.variant.ProductVariant;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String sku;
    private String thumbnail;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal weight;
    private Integer productQuantity;
    private Date productDateAvailable;
    private String region;
    private Boolean available;
    private Integer productQuantityOrderMin;
    private Integer productQuantityOrderMax;
    private String collection;
    private String seUrl;
    private String metaTagTitle;
    private String metaTagKeywords;
    private String metaTagDescription;
    private Set<ProductVariantDto> variants;
    private ManufacturerDto manufacturer;
    private MerchantStoreDto merchantStore;
    private boolean deleted;
}

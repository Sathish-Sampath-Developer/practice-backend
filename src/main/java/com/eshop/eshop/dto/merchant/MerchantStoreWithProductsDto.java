package com.eshop.eshop.dto.merchant;

import com.eshop.eshop.dto.product.ProductDtoWithoutMerchantAndManufacturer;
import com.eshop.eshop.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantStoreWithProductsDto {
    private Long id;
    private Boolean retailer;
    private String storeName;
    private String storePhone;
    private String address_line_one;
    private String address_line_two;
    private String storeCity;
    private String storeState;
    private String storePin;
    private String storeCountry;
    private String dateBusinessSince;
    private String defaultLanguage;
    private List<String> languages;
    private String storeTemplate;
    private String invoiceTemplate;
    private String storeEmailAddress;
    private String storeLogo;
    private String currency;
//    private boolean deleted;
    private List<ProductDtoWithoutMerchantAndManufacturer> products;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
}

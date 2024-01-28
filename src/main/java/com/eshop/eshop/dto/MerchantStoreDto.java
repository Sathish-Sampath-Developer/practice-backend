package com.eshop.eshop.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantStoreDto {
    private Long id;
    private Boolean retailer;
    private String storeName;
    private String code;
    private String storePhone;
    private String storeAddress;
    private String storeCity;
    private String storePostalCode;
    private String country;
    private String zone;
    private String storeStateProvince;
    private String dateBusinessSince;
    private String defaultLanguage;
    private List<String> languages;
    private String storeTemplate;
    private String invoiceTemplate;
    private String storeEmailAddress;
    private String storeLogo;
    private String currency;
    private boolean currencyFormatNational;
    private boolean deleted;
}

package com.eshop.eshop.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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
    private String address_line_one;
    private String address_line_two;
    private String storeCity;
    private String state;
    private String storePin;
    private String country;
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

package com.eshop.eshop.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MERCHANT_STORE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantStoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MERCHANT_STORE_ID")
    private Long id;

    @Column(name = "IS_RETAILER")
    private Boolean retailer = false;

    @NotEmpty
    @Column(name = "STORE_NAME", nullable = false, length = 100)
    private String storeName;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    @Column(name = "STORE_CODE", nullable = false, unique = true, length = 100)
    private String code;

    @NotEmpty
    @Column(name = "STORE_PHONE", length = 50)
    private String storePhone;

    @Column(name = "ADDRESS_LINE_ONE")
    private String address_line_one;

    @Column(name = "ADDRESS_LINE_TWO")
    private String address_line_two;

    @NotEmpty
    @Column(name = "STORE_CITY", length = 100)
    private String storeCity;

    @Column(name = "STORE_STATE")
    private String state;

    @NotEmpty
    @Column(name = "STORE_PIN", length = 15)
    private String storePin;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STORE_STATE_PROV", length = 100)
    private String storeStateProvince;

    @Column(name = "DATE_BUSINESS_SINCE")
    private String dateBusinessSince;

    @Column(name = "LANGUAGE")
    private String defaultLanguage;

    @NotEmpty
    @Column(name = "LANGUAGES")
    private List<String> languages = new ArrayList<>();

    @Column(name = "STORE_TEMPLATE", length = 25)
    private String storeTemplate;

    @Column(name = "INVOICE_TEMPLATE", length = 25)
    private String invoiceTemplate;

    @Email
    @NotEmpty
    @Column(name = "STORE_EMAIL", length = 60, nullable = false)
    private String storeEmailAddress;

    @Column(name = "STORE_LOGO", length = 100)
    private String storeLogo;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "CURRENCY_FORMAT_NATIONAL")
    private boolean currencyFormatNational;

    @Column(name = "DELETED")
    private boolean deleted = false;

}

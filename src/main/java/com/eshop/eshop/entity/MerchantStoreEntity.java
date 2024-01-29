package com.eshop.eshop.entity;


import com.eshop.eshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
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
    @Column(name = "MERCHANT_ID")
    private Long id;

    @Column(name = "IS_RETAILER")
    private Boolean retailer = false;

    @OneToMany(mappedBy = "merchantStore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    @NotEmpty
    @Column(name = "STORE_NAME", nullable = false, length = 100)
    private String storeName;

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
    private String storeState;

    @NotEmpty
    @Column(name = "STORE_PIN", length = 15)
    private String storePin;

    @Column(name = "COUNTRY")
    private String country;

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

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 60)
    private String modifiedBy;
}

package com.eshop.eshop.dto.manufacturer;

import com.eshop.eshop.dto.product.ProductDto;
import com.eshop.eshop.dto.product.ProductDtoWithoutMerchantAndManufacturer;
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
public class ManufacturerWithProductsDto {
    private Long id;
    private String manufacturer_image;
    private String manufacturer_name;
    private String manufacturer_email;
    private String manufacturer_phone;
    private String address_line_one;
    private String address_line_two;
    private String city;
    private String state;
    private String country;
    private String pin;
    private List<String> orders;
//    private boolean deleted;
    private List<ProductDtoWithoutMerchantAndManufacturer> products;
    private Date dateCreated;
    private Date dateModified;
    private String createdBy;
    private String modifiedBy;
}

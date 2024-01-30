package com.eshop.eshop.entity;

import com.eshop.eshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MANUFACTURER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManufacturerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANUFACTURER_ID")
    private Long id;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    @Column(name = "MANUFACTURER_IMAGE")
    private String manufacturer_image;

    @Column(name = "MANUFACTURE_NAME")
    private String manufacturer_name;

    @Column(name = "MANUFACTURER_EMAIL")
    private String manufacturer_email;

    @Column(name = "MANUFACTURER_PHONE")
    private String manufacturer_phone;

    @Column(name = "ADDRESS_LINE_ONE")
    private String address_line_one;

    @Column(name = "ADDRESS_LINE_TWO")
    private String address_line_two;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PIN")
    private String pin;

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

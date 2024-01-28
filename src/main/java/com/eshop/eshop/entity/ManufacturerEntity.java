package com.eshop.eshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
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

    @Column(name = "MANUFACTURER_IMAGE")
    private String manufacturer_image;

    @Column(name = "MANUFACTURE_NAME")
    private String manufacturer_name;

    @Column(name = "MANUFACTURER_EMAIL")
    private String manufacturer_email;

    @Column(name = "MANUFACTURER_PHONE")
    private String manufacturer_phone;

    @Column(name = "MANUFACTURER_ADDRESS")
    private String manufacturer_address;

    @NotEmpty
    @Column(name = "MANUFACTURER_CODE", length = 100, nullable = false)
    private String manufacturer_code;

    @Column(name = "ORDERS")
    private List<String> orders;

    @Column(name = "DELETED")
    private boolean deleted = false;

}

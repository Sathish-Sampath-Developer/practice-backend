package com.eshop.eshop.entity;


import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "taxes")
public class TaxClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tax_id")
    private Long tax_id;

    @NotEmpty
    @Column(name = "tax_class_code",nullable = false,length = 10)
    private String code;

    @NotEmpty
    @Column(name = "tax_class_title",nullable = false,length = 32)
    private String title;

//    @OneToMany(mappedBy = "taxClass",targetEntity = ProductEntity.class)
//    private List<ProductEntity> products = new ArrayList<ProductEntity>();

//    @OneToMany(mappedBy = "taxClass",targetEntity = ProductEntity.class)
//    private List<ProductEntity> products = new ArrayList<ProductEntity>();
}

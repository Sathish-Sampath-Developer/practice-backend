package com.eshop.eshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

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

    @Column(name = "CODE")
    private String code;

    @Column(name = "DELETED")
    private boolean deleted = false;
}

package com.eshop.eshop.entity.Address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

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
}

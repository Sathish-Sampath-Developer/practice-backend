package com.eshop.eshop.service;

import com.eshop.eshop.entity.AddressEntity;

import java.util.List;

public interface AddressService {
    List<AddressEntity> getAllAddress();
    AddressEntity getAddressById(Long id);
    AddressEntity saveAddress(AddressEntity address);
    AddressEntity updateAddress(Long id, AddressEntity address);
    void deleteAddress(Long id);
}

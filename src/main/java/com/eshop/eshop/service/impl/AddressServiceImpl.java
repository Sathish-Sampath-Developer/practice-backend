package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.AddressEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.AddressRepository;
import com.eshop.eshop.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAllAddress() {
        return addressRepository.findByDeletedFalse();
    }

    @Override
    public AddressEntity getAddressById(Long id) {
        return addressRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Address was not found given id"));
    }

    @Override
    public AddressEntity saveAddress(AddressEntity address) {
        return addressRepository.save(address);
    }

    @Override
    public AddressEntity updateAddress(Long id, AddressEntity address) {
        AddressEntity toUpdate = addressRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Address was not found given id"));

        toUpdate.setAddress_line_one(address.getAddress_line_one());
        toUpdate.setAddress_line_two(address.getAddress_line_two());
        toUpdate.setState(address.getState());
        toUpdate.setCity(address.getCity());
        toUpdate.setPin(address.getPin());
        toUpdate.setCountry(address.getCountry());
        toUpdate.setCode(address.getCode());

        return addressRepository.save(toUpdate);
    }

    @Override
    public void deleteAddress(Long id) {
        AddressEntity address = addressRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Address was not found given id"));
        address.setDeleted(true);
        addressRepository.save(address);
    }
}

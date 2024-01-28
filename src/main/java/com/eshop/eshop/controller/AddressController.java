package com.eshop.eshop.controller;

import com.eshop.eshop.entity.AddressEntity;
import com.eshop.eshop.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/address")
@Tag(name = "Address Controller", description = "This url for CRUD of Address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressEntity> createAddress(@RequestBody AddressEntity address) {
        return new ResponseEntity<>(addressService.saveAddress(address), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressEntity>> getListOfAddress() {
        return new ResponseEntity<>(addressService.getAllAddress(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressEntity> getAAddress(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressEntity> updateAddress(@PathVariable(name = "id") Long id, @RequestBody AddressEntity address) {
        return new ResponseEntity<>(addressService.updateAddress(id, address), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable(name = "id") Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.eshop.eshop.service;

import com.eshop.eshop.entity.ManufacturerEntity;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerEntity> getAllManufacturer();
    ManufacturerEntity getManufacturerById(Long id);
    ManufacturerEntity createManufacturer(ManufacturerEntity manufacturer);
    ManufacturerEntity updateManufacturer(Long id, ManufacturerEntity manufacturer);
    void deleteManufacturer(Long id);
}

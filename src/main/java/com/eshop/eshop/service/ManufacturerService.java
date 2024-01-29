package com.eshop.eshop.service;

import com.eshop.eshop.dto.manufacturer.ManufacturerDto;
import com.eshop.eshop.dto.manufacturer.ManufacturerWithProductsDto;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerWithProductsDto> getAllManufacturer();
    ManufacturerWithProductsDto getManufacturerById(Long id);
    ManufacturerWithProductsDto createManufacturer(ManufacturerDto manufacturerDto);
    ManufacturerWithProductsDto updateManufacturer(Long id, ManufacturerDto manufacturerDto);
    void deleteManufacturer(Long id);
}
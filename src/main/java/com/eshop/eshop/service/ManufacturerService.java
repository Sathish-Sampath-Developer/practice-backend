package com.eshop.eshop.service;

import com.eshop.eshop.dto.ManufacturerDto;
import com.eshop.eshop.dto.ManufacturerWithProductsDto;
import com.eshop.eshop.entity.ManufacturerEntity;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerWithProductsDto> getAllManufacturer();
    ManufacturerDto getManufacturerById(Long id);
    ManufacturerDto createManufacturer(ManufacturerDto manufacturerDto);
    ManufacturerDto updateManufacturer(Long id, ManufacturerDto manufacturerDto);
    void deleteManufacturer(Long id);
}
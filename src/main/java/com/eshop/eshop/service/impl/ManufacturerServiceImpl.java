package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.manufacturer.ManufacturerDto;
import com.eshop.eshop.dto.manufacturer.ManufacturerWithProductsDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.manufacturer.ManufacturerMapper;
import com.eshop.eshop.repository.ManufacturerRepository;
import com.eshop.eshop.service.AuthService;
import com.eshop.eshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;
    private final AuthService authService;

    @Override
    public List<ManufacturerWithProductsDto> getAllManufacturer() {
        List<ManufacturerEntity> listOfManufacturer = manufacturerRepository.findAll();

        return listOfManufacturer.stream().map(manufacturerMapper::convertToDtoWithProducts).toList();
    }

    @Override
    public ManufacturerWithProductsDto getManufacturerById(Long id) {

        ManufacturerEntity manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));

        return manufacturerMapper.convertToDtoWithProducts(manufacturer);
    }

    @Override
    public ManufacturerWithProductsDto createManufacturer(ManufacturerDto manufacturerDto) {

        ManufacturerEntity manufacturer = manufacturerMapper.convertToEntity(manufacturerDto);

        manufacturer.setCreatedBy(authService.getAuthenticatedUsername());

        return manufacturerMapper.convertToDtoWithProducts(manufacturerRepository.save(manufacturer));
    }

    @Override
    public ManufacturerWithProductsDto updateManufacturer(Long id, ManufacturerDto manufacturerDto) {

        ManufacturerEntity manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));

        ManufacturerEntity updatedManufacturer = manufacturerMapper.convertToEntity(manufacturerDto);

        updatedManufacturer.setModifiedBy(authService.getAuthenticatedUsername());

        return manufacturerMapper.convertToDtoWithProducts(manufacturerRepository.save(updatedManufacturer));
    }

    @Override
    public void deleteManufacturer(Long id) {
        ManufacturerEntity toDelete = manufacturerRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));
        manufacturerRepository.delete(toDelete);
    }
}

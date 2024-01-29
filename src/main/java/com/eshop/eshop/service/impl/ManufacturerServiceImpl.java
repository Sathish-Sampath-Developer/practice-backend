package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ManufacturerDto;
import com.eshop.eshop.dto.ManufacturerWithProductsDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.ManufacturerMapper;
import com.eshop.eshop.mapper.ManufacturerWithProductsMapper;
import com.eshop.eshop.repository.ManufacturerRepository;
import com.eshop.eshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    private final ManufacturerMapper manufacturerMapper;

    @Autowired
    private final ManufacturerWithProductsMapper manufacturerWithProductsMapper;

    @Override
    public List<ManufacturerWithProductsDto> getAllManufacturer() {
        List<ManufacturerEntity> listOfManufacturer = manufacturerRepository.findAll();

        return listOfManufacturer.stream().map(manufacturerWithProductsMapper::convertToDto).toList();
    }

    @Override
    public ManufacturerDto getManufacturerById(Long id) {

        ManufacturerEntity manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));

        return manufacturerMapper.convertToDto(manufacturer);
    }

    @Override
    public ManufacturerDto createManufacturer(ManufacturerDto manufacturerDto) {

        ManufacturerEntity manufacturer = manufacturerRepository.save(manufacturerMapper.convertToEntity(manufacturerDto));

        return manufacturerMapper.convertToDto(manufacturer);
    }

    @Override
    public ManufacturerDto updateManufacturer(Long id, ManufacturerDto manufacturerDto) {
       ManufacturerEntity manufacturer =  manufacturerRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));

       ManufacturerEntity updatedManufacturer = manufacturerMapper.convertToEntity(manufacturerDto);

        return manufacturerMapper.convertToDto(manufacturerRepository.save(updatedManufacturer));
    }

    @Override
    public void deleteManufacturer(Long id) {
        ManufacturerEntity toDelete =  manufacturerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));
        toDelete.setDeleted(true);
        manufacturerRepository.delete(toDelete);
    }
}

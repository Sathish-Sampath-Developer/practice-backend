package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.exception.ServiceException;
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

    @Override
    public List<ManufacturerEntity> getAllManufacturer() {
        return manufacturerRepository.findByDeletedFalse();
    }

    @Override
    public ManufacturerEntity getManufacturerById(Long id) {
        return manufacturerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));
    }

    @Override
    public ManufacturerEntity createManufacturer(ManufacturerEntity manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public ManufacturerEntity updateManufacturer(Long id, ManufacturerEntity manufacturer) {
       ManufacturerEntity toUpdate =  manufacturerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));

       toUpdate.setManufacturer_code(manufacturer.getManufacturer_code());
       toUpdate.setManufacturer_address(manufacturer.getManufacturer_address());
       toUpdate.setManufacturer_email(manufacturer.getManufacturer_email());
       toUpdate.setManufacturer_name(manufacturer.getManufacturer_name());
       toUpdate.setManufacturer_phone(manufacturer.getManufacturer_phone());
       toUpdate.setManufacturer_image(manufacturer.getManufacturer_image());
       toUpdate.setOrders(manufacturer.getOrders());

        return manufacturerRepository.save(toUpdate);
    }

    @Override
    public void deleteManufacturer(Long id) {
        ManufacturerEntity toDelete =  manufacturerRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Manufacturer was not found given id"));
        toDelete.setDeleted(true);
        manufacturerRepository.delete(toDelete);
    }
}

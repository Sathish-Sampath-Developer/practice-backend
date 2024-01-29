package com.eshop.eshop.controller;


import com.eshop.eshop.dto.manufacturer.ManufacturerDto;
import com.eshop.eshop.dto.manufacturer.ManufacturerWithProductsDto;
import com.eshop.eshop.service.ManufacturerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/manufacturers")
@AllArgsConstructor
@Tag(name = "Manufacturer Controller", description = "This url for CRUD of Manufacturer")
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<ManufacturerWithProductsDto> createManufacturer(@RequestBody ManufacturerDto manufacturer) {
        return new ResponseEntity<>(manufacturerService.createManufacturer(manufacturer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerWithProductsDto>> getManufacturerList() {
        return new ResponseEntity<>(manufacturerService.getAllManufacturer(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerWithProductsDto> getManufacturerById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(manufacturerService.getManufacturerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerWithProductsDto> updateMerchant(@PathVariable(name = "id") Long id, @RequestBody ManufacturerDto manufacturer) {
        return new ResponseEntity<>(manufacturerService.updateManufacturer(id, manufacturer), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

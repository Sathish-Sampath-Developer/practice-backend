package com.eshop.eshop.controller;

import com.eshop.eshop.dto.MerchantStoreDto;
import com.eshop.eshop.service.MerchantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/merchants")
@AllArgsConstructor
@Tag(name = "Merchant Controller", description = "This url for CRUD of Merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantStoreDto> createMerchant(@RequestBody MerchantStoreDto merchantStoreDto) {
        return new ResponseEntity<>(merchantService.createMerchant(merchantStoreDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MerchantStoreDto>> getMerchantList() {
        return new ResponseEntity<>(merchantService.getMerchantList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantStoreDto> getMerchantById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(merchantService.getMerchantById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantStoreDto> updateMerchant(@PathVariable(name = "id") Long id, @RequestBody MerchantStoreDto merchantStoreDto) {
        return new ResponseEntity<>(merchantService.updateMerchant(id, merchantStoreDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        merchantService.deleteMerchant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

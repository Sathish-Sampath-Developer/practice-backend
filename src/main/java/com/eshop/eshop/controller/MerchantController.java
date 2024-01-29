package com.eshop.eshop.controller;

import com.eshop.eshop.dto.merchant.MerchantStoreDto;
import com.eshop.eshop.dto.merchant.MerchantStoreWithProductsDto;
import com.eshop.eshop.service.MerchantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/merchants")
@AllArgsConstructor
@Tag(name = "Merchant Controller", description = "This url for CRUD of Merchants")
public class MerchantController {

    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantStoreWithProductsDto> createMerchant(@RequestBody MerchantStoreDto merchantStoreDto) {
        return new ResponseEntity<>(merchantService.createMerchant(merchantStoreDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MerchantStoreWithProductsDto>> getMerchantList() {
        return new ResponseEntity<>(merchantService.getMerchantList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantStoreWithProductsDto> getMerchantById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(merchantService.getMerchantById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantStoreWithProductsDto> updateMerchant(@PathVariable(name = "id") Long id, @RequestBody MerchantStoreDto merchantStoreDto) {
        return new ResponseEntity<>(merchantService.updateMerchant(id, merchantStoreDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        merchantService.deleteMerchant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

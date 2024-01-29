package com.eshop.eshop.service;

import com.eshop.eshop.dto.merchant.MerchantStoreDto;
import com.eshop.eshop.dto.merchant.MerchantStoreWithProductsDto;

import java.util.List;

public interface MerchantService {
    MerchantStoreWithProductsDto createMerchant(MerchantStoreDto merchantStoreDto);

    List<MerchantStoreWithProductsDto> getMerchantList();

    MerchantStoreWithProductsDto getMerchantById(Long id);

    MerchantStoreWithProductsDto updateMerchant(long id, MerchantStoreDto merchantStoreDto);

    void deleteMerchant(long id);
}

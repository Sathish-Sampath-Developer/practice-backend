package com.eshop.eshop.service;

import com.eshop.eshop.dto.MerchantStoreDto;

import java.util.List;

public interface MerchantService {
    MerchantStoreDto createMerchant(MerchantStoreDto merchantStoreDto);

    List<MerchantStoreDto> getMerchantList();

    MerchantStoreDto getMerchantById(Long id);

    MerchantStoreDto updateMerchant(long id, MerchantStoreDto merchantStoreDto);

    void deleteMerchant(long id);
}

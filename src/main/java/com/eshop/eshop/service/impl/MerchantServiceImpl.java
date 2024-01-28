package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.MerchantStoreDto;
import com.eshop.eshop.entity.MerchantStoreEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.MerchantMapper;
import com.eshop.eshop.repository.MerchantRepository;
import com.eshop.eshop.service.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    private MerchantMapper mapper;

    @Override
    public MerchantStoreDto createMerchant(MerchantStoreDto merchantDto) {
        MerchantStoreEntity merchantStore = merchantRepository.save(mapper.convertToEntity(merchantDto));
        return mapper.convertToDto(merchantStore);
    }

    @Override
    public List<MerchantStoreDto> getMerchantList() {
        List<MerchantStoreEntity> listOfMerchant = merchantRepository.findByDeletedFalse();
        return listOfMerchant.stream().map((store) -> mapper.convertToDto(store)).collect(Collectors.toList());
    }

    @Override
    public MerchantStoreDto getMerchantById(Long id) {
        MerchantStoreEntity merchantStore = merchantRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Merchant was not found in given id"));
        return mapper.convertToDto(merchantStore);
    }

    @Override
    public MerchantStoreDto updateMerchant(long id, MerchantStoreDto merchantStore) {
        MerchantStoreEntity toUpdate = merchantRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Merchant was not found in given id"));

        MerchantStoreEntity updatedMerchant = merchantRepository.save(mapper.convertToEntity(merchantStore));

        return mapper.convertToDto(updatedMerchant);
    }

    @Override
    public void deleteMerchant(long id) {
        MerchantStoreEntity merchantStore = merchantRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Merchant was not found in given id"));

        merchantStore.setDeleted(true);

        merchantRepository.save(merchantStore);
    }
}

package com.eshop.eshop.mapper.merchant;

import com.eshop.eshop.dto.merchant.MerchantStoreDto;
import com.eshop.eshop.entity.MerchantStoreEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MerchantMapper {

    private final ModelMapper mapper;

    public MerchantStoreEntity convertToEntity(MerchantStoreDto merchantStoreDto) {
        return mapper.map(merchantStoreDto, MerchantStoreEntity.class);
    }

    public MerchantStoreDto convertToDto(MerchantStoreEntity merchantStore){
        return mapper.map(merchantStore,MerchantStoreDto.class);
    }


}

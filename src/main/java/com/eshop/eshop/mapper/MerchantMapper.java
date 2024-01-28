package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.MerchantStoreDto;
import com.eshop.eshop.entity.MerchantStoreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {

    private final ModelMapper mapper;

    public MerchantMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MerchantStoreEntity convertToEntity(MerchantStoreDto merchantStoreDto) {
        return mapper.map(merchantStoreDto, MerchantStoreEntity.class);
    }

    public MerchantStoreDto convertToDto(MerchantStoreEntity merchantStore){
        return mapper.map(merchantStore,MerchantStoreDto.class);
    }
}

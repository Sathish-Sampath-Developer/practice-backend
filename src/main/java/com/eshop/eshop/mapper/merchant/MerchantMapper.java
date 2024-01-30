package com.eshop.eshop.mapper.merchant;

import com.eshop.eshop.dto.merchant.MerchantStoreDto;
import com.eshop.eshop.dto.merchant.MerchantStoreWithProductsDto;
import com.eshop.eshop.dto.product.ProductDtoWithoutMerchantAndManufacturer;
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

    public MerchantStoreWithProductsDto convertToDtoWithProducts(MerchantStoreEntity merchantStore){

        MerchantStoreWithProductsDto merchantStoreDto = new MerchantStoreWithProductsDto();

        merchantStoreDto.setId(merchantStore.getId());
        merchantStoreDto.setRetailer(merchantStore.getRetailer());
        merchantStoreDto.setStoreName(merchantStore.getStoreName());
        merchantStoreDto.setStorePhone(merchantStore.getStorePhone());
        merchantStoreDto.setAddress_line_one(merchantStore.getAddress_line_one());
        merchantStoreDto.setAddress_line_two(merchantStore.getAddress_line_two());
        merchantStoreDto.setStoreCity(merchantStore.getStoreCity());
        merchantStoreDto.setStoreState(merchantStore.getStoreState());
        merchantStoreDto.setStorePin(merchantStore.getStorePin());
        merchantStoreDto.setStoreCountry(merchantStore.getStorePin());
        merchantStoreDto.setDateBusinessSince(merchantStore.getDateBusinessSince());
        merchantStoreDto.setDefaultLanguage(merchantStore.getDefaultLanguage());
        merchantStoreDto.setLanguages(merchantStore.getLanguages());
        merchantStoreDto.setStoreTemplate(merchantStore.getStoreTemplate());
        merchantStoreDto.setInvoiceTemplate(merchantStore.getInvoiceTemplate());
        merchantStoreDto.setStoreEmailAddress(merchantStore.getStoreEmailAddress());
        merchantStoreDto.setStoreLogo(merchantStore.getStoreLogo());
        merchantStoreDto.setCurrency(merchantStore.getCurrency());
//        merchantStoreDto.setDeleted(merchantStore.isDeleted());
        merchantStoreDto.setProducts(merchantStore.getProducts().stream().map(productEntity -> mapper.map(productEntity, ProductDtoWithoutMerchantAndManufacturer.class)).toList());
        merchantStoreDto.setDateCreated(merchantStore.getDateCreated());
        merchantStoreDto.setDateModified(merchantStore.getDateModified());
        merchantStoreDto.setCreatedBy(merchantStore.getCreatedBy());
        merchantStoreDto.setModifiedBy(merchantStore.getModifiedBy());


        return merchantStoreDto;
    }


}

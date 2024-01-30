package com.eshop.eshop.mapper.manufacturer;

import com.eshop.eshop.dto.manufacturer.ManufacturerDto;
import com.eshop.eshop.dto.manufacturer.ManufacturerWithProductsDto;
import com.eshop.eshop.dto.product.ProductDtoWithoutMerchantAndManufacturer;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.mapper.product.ProductMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManufacturerMapper {

//    @Autowired
    private final ModelMapper mapper;

    public ManufacturerEntity convertToEntity(ManufacturerDto manufacturerDto) {
        return mapper.map(manufacturerDto, ManufacturerEntity.class);
    }

    public ManufacturerDto convertToDto(ManufacturerEntity manufacturerEntity) {
        return mapper.map(manufacturerEntity, ManufacturerDto.class);
    }

    public ManufacturerWithProductsDto convertToDtoWithProducts(ManufacturerEntity manufacturerEntity) {

        ManufacturerWithProductsDto manufacturerWithProductsDto = new ManufacturerWithProductsDto();

        manufacturerWithProductsDto.setId(manufacturerEntity.getId());
        manufacturerWithProductsDto.setManufacturer_image(manufacturerEntity.getManufacturer_image());
        manufacturerWithProductsDto.setManufacturer_name(manufacturerEntity.getManufacturer_name());
        manufacturerWithProductsDto.setManufacturer_email(manufacturerEntity.getManufacturer_email());
        manufacturerWithProductsDto.setManufacturer_phone(manufacturerEntity.getManufacturer_phone());
        manufacturerWithProductsDto.setAddress_line_one(manufacturerEntity.getAddress_line_one());
        manufacturerWithProductsDto.setAddress_line_two(manufacturerEntity.getAddress_line_two());
        manufacturerWithProductsDto.setCity(manufacturerEntity.getCity());
        manufacturerWithProductsDto.setState(manufacturerEntity.getState());
        manufacturerWithProductsDto.setCountry(manufacturerEntity.getCountry());
        manufacturerWithProductsDto.setPin(manufacturerEntity.getPin());
//        manufacturerWithProductsDto.setOrders(manufacturerEntity.getOrders());
//        manufacturerWithProductsDto.setDeleted(manufacturerEntity.isDeleted());
        manufacturerWithProductsDto.setProducts(manufacturerEntity.getProducts().stream().map(product -> mapper.map(product, ProductDtoWithoutMerchantAndManufacturer.class)).toList());
        manufacturerWithProductsDto.setDateCreated(manufacturerEntity.getDateCreated());
        manufacturerWithProductsDto.setDateModified(manufacturerEntity.getDateModified());
        manufacturerWithProductsDto.setCreatedBy(manufacturerEntity.getCreatedBy());
        manufacturerWithProductsDto.setModifiedBy(manufacturerEntity.getModifiedBy());

        return manufacturerWithProductsDto;
    }

    public ManufacturerEntity convertToEntityWithProducts(ManufacturerWithProductsDto manufacturerWithProductsDto) {
        return mapper.map(manufacturerWithProductsDto, ManufacturerEntity.class);
    }
}

package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ManufacturerWithProductsDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ManufacturerWithProductsMapper {

    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private final ProductMapper productMapper;

    public ManufacturerEntity convertToEntity(ManufacturerWithProductsDto manufacturerWithProductsDto) {
        return mapper.map(manufacturerWithProductsDto, ManufacturerEntity.class);
    }

    public ManufacturerWithProductsDto convertToDto(ManufacturerEntity manufacturerEntity) {

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
        manufacturerWithProductsDto.setOrders(manufacturerEntity.getOrders());
        manufacturerWithProductsDto.setDeleted(manufacturerEntity.isDeleted());
        manufacturerWithProductsDto.setProducts(manufacturerEntity.getProducts().stream().map(productMapper::convertToDto).toList());

        return manufacturerWithProductsDto;
    }
}

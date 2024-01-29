package com.eshop.eshop.mapper;

import com.eshop.eshop.dto.ManufacturerDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {

    private final ModelMapper mapper;

    @Autowired
    public ManufacturerMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ManufacturerEntity convertToEntity(ManufacturerDto manufacturerDto) {
        return mapper.map(manufacturerDto, ManufacturerEntity.class);
    }

    public ManufacturerDto convertToDto(ManufacturerEntity manufacturerEntity) {
        return mapper.map(manufacturerEntity, ManufacturerDto.class);
    }
}

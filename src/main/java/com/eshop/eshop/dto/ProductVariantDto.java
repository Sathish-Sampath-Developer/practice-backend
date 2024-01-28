package com.eshop.eshop.dto;

import com.eshop.eshop.entity.AuditSection;
import com.eshop.eshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductVariantDto {
    private Long id;
    private String size;
    private String color;
    private int quantity;
    private String image;
    private Date dateAvailable;
    private boolean available;
}

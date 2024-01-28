package com.eshop.eshop.entity.product.variant;

import com.eshop.eshop.entity.AuditSection;
import com.eshop.eshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_VARIANT")
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {

    @Id
    @Column(name = "PRODUCT_VARIANT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Setter
    @Getter
    @NotNull
    @Column(name = "SIZE")
    private String size;

    @Setter
    @Getter
    @NotNull
    @Column(name = "COLOR")
    private String color;

    @Getter
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @Getter
    @Setter
    @NotNull
    @Column(name = "IMAGE")
    private String image;

    @Setter
    @Getter
    @Column(name = "DATE_AVAILABLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAvailable = new Date();

    @Setter
    @Getter
    @Column(name = "AVAILABLE")
    private boolean available = true;

    @Setter
    @Getter
    @ManyToOne(targetEntity = ProductEntity.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity product;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = (quantity > 0);
    }

    @Column(name = "DELETED")
    @Setter
    @Getter
    private boolean deleted = false;

}

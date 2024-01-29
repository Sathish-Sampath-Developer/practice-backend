package com.eshop.eshop.entity.product.variant;

import com.eshop.eshop.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "PRODUCT_VARIANT")
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {

    @Getter
    @Setter
    @Id
    @Column(name = "PRODUCT_VARIANT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreationTimestamp
    @Getter
    @Setter
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @UpdateTimestamp
    @Getter
    @Setter
    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Getter
    @Setter
    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Getter
    @Setter
    @Column(name = "MODIFIED_BY", length = 60)
    private String modifiedBy;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = (quantity > 0);
    }
}

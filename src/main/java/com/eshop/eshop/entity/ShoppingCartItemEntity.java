package com.eshop.eshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "SHOPPING_CART_ITEMS")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartItemEntity {

    @Id
    @Column(name = "SHOPPING_CART_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ShoppingCartEntity.class)
    @JoinColumn(name = "SHP_CART_ID", nullable = false)
    private ShoppingCartEntity shoppingCart;

    @Column(name = "QUANTITY")
    private double quantity = 1;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "SKU", nullable = true)
    private String sku;

    @Column(name = "ITEM_PRICE", nullable = true)
    private double itemPrice;

    @Column(name = "SUB_TOTAL", nullable = true)
    private double subTotal = 0;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 60)
    private String modifiedBy;

    public ShoppingCartItemEntity(Long id, double quantity, Long productId, String sku, double itemPrice, double subTotal, boolean deleted) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.sku = sku;
        this.itemPrice = itemPrice;
        this.subTotal = itemPrice * quantity;
    }
}


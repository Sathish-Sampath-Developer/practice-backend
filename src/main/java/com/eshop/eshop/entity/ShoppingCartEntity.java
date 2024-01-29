package com.eshop.eshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SHOPPING_CART")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private Set<ShoppingCartItemEntity> lineItems = new HashSet<ShoppingCartItemEntity>();

    @Column(name = "SUB_TOTAL")
    private double subTotal = 0;

    @Column(name = "NO_OF_ITEMS")
    private int noOfItems  = 0;

    @Column(name = "SHIPPING")
    private String shipping;

    @Column(name = "MERCHANT_STORE")
    private String merchantStore;

    @Column(name = "CUSTOMER_ID", nullable = true)
    private Long customerId;

    @Column(name = "ORDER_ID", nullable = true)
    private Long orderId;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "PROMO_CODE")
    private String promoCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROMO_ADDED")
    private Date promoAdded;

    @Column(name = "DELETED")
    private boolean deleted = false;

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
}

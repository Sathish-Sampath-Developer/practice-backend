package com.eshop.eshop.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "COLLECTIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLLECTION_ID")
    private Long id;

    @Column(name = "COLLECTION_TITLE")
    private String title;

    @Column(name = "COLLECTION_STATUS")
    private boolean collectionStatus;
}

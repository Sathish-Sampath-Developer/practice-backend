package com.eshop.eshop.service;

import com.eshop.eshop.entity.CollectionEntity;

import java.util.List;

public interface CollectionService {
    List<CollectionEntity> getListOfCollections();
    CollectionEntity getCollectionById(Long id);
    CollectionEntity saveCollection(CollectionEntity category);
    CollectionEntity updateCollection(Long id, CollectionEntity category);
    void deleteCollection(Long id);
}

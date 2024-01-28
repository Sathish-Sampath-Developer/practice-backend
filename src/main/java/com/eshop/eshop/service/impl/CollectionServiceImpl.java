package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.CollectionEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.CollectionRepository;
import com.eshop.eshop.service.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private final CollectionRepository collectionRepository;

    @Override
    public List<CollectionEntity> getListOfCollections() {
        return collectionRepository.findByDeletedFalse();
    }

    @Override
    public CollectionEntity getCollectionById(Long id) {
        return collectionRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));
    }

    @Override
    public CollectionEntity saveCollection(CollectionEntity collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public CollectionEntity updateCollection(Long id, CollectionEntity collection) {
        CollectionEntity updatedCollection = collectionRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));

        updatedCollection.setCollectionStatus(collection.isCollectionStatus());
        updatedCollection.setTitle(collection.getTitle());

        return collectionRepository.save(updatedCollection);
    }

    @Override
    public void deleteCollection(Long id) {
        CollectionEntity collection = collectionRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));
        collection.setDeleted(true);
        collectionRepository.save(collection);
    }
}

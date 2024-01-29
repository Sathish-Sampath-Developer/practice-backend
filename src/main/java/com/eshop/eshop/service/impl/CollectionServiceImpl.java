package com.eshop.eshop.service.impl;

import com.eshop.eshop.entity.CollectionEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.repository.CollectionRepository;
import com.eshop.eshop.service.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Override
    public List<CollectionEntity> getListOfCollections() {
        return collectionRepository.findAll();
    }

    @Override
    public CollectionEntity getCollectionById(Long id) {
        return collectionRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));
    }

    @Override
    public CollectionEntity saveCollection(CollectionEntity collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public CollectionEntity updateCollection(Long id, CollectionEntity collection) {

        CollectionEntity updatedCollection = collectionRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));

        updatedCollection.setCollectionStatus(collection.isCollectionStatus());
        updatedCollection.setTitle(collection.getTitle());

        return collectionRepository.save(updatedCollection);
    }

    @Override
    public void deleteCollection(Long id) {

        CollectionEntity collection = collectionRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Collection was not found given id"));

        collectionRepository.delete(collection);
    }
}

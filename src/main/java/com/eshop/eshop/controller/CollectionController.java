package com.eshop.eshop.controller;

import com.eshop.eshop.entity.CollectionEntity;
import com.eshop.eshop.service.CollectionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/collections")
@AllArgsConstructor
@Tag(name = "Collection Controller", description = "This url for CRUD of Category")
public class CollectionController {

    private CollectionService collectionService;

    @PostMapping
    public ResponseEntity<CollectionEntity> createCategory(@RequestBody CollectionEntity category) {
        return new ResponseEntity<>(collectionService.saveCollection(category), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CollectionEntity>> getListOfCategory() {
        return new ResponseEntity<>(collectionService.getListOfCollections(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionEntity> getACategory(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(collectionService.getCollectionById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionEntity> updateCategory(@PathVariable(name = "id") Long id, @RequestBody CollectionEntity category) {
        return new ResponseEntity<>(collectionService.updateCollection(id, category), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        collectionService.deleteCollection(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

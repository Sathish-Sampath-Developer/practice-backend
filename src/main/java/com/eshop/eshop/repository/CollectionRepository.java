package com.eshop.eshop.repository;

import com.eshop.eshop.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<CollectionEntity,Long> {
//    List<CollectionEntity> findByDeletedFalse();
//    Optional<CollectionEntity> findByIdAndDeletedFalse(Long id);
}

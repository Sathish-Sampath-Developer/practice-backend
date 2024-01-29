package com.eshop.eshop.repository;

import com.eshop.eshop.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {
//    List<ImageEntity> findByDeletedFalse();
//    Optional<ImageEntity> findByIdAndDeletedFalse(Long id);

}

package com.eshop.eshop.repository;

import com.eshop.eshop.entity.MerchantStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantStoreEntity,Long> {
//    List<MerchantStoreEntity> findByDeletedFalse();
//    Optional<MerchantStoreEntity> findByIdAndDeletedFalse(Long id);

}

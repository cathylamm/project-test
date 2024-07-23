package com.fsse2406.project.repository;

import com.fsse2406.project.data.cartItem.entity.CartItemEntity;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartIemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByProductAndUser(ProductEntity product, UserEntity user);
    // 2nd method
    // Optional<CartItemEntity>findByProductPidAndUserUid(Integer pid, Integer uid);

    List<CartItemEntity> findAllByUser(UserEntity userEntity);

    Integer removeByProductPidAndUserUid(Integer pid, String firebaseUid);
}

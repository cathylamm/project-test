package com.fsse2406.project.repository;

import com.fsse2406.project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    boolean existsByName(String name);
    Optional<ProductEntity> findByPid(Integer pid);


}

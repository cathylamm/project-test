package com.fsse2406.project.service.impl;

import com.fsse2406.project.data.product.domainObject.request.CreateProductRequestData;
import com.fsse2406.project.data.product.domainObject.response.CreateProductResponseData;
import com.fsse2406.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.entity.ProductEntity;
import com.fsse2406.project.exception.product.ProductExistedException;
import com.fsse2406.project.exception.product.ProductNotFoundException;
import com.fsse2406.project.repository.ProductRepository;
import com.fsse2406.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

//    private List<ProductEntity> productEntityList = new ArrayList<>();

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData) {
        try {
            if (isExistsByName((createProductRequestData.getName()))) {
                throw new ProductExistedException(createProductRequestData.getName());
            }
            return new CreateProductResponseData(
                    productRepository.save(new ProductEntity(createProductRequestData))
            );
        } catch (Exception ex) {
            logger.warn("Create Product: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<GetAllProductsResponseData> getAllProducts() {
        List<GetAllProductsResponseData> getAllProductsResponseDataList = new ArrayList<>();

        for (ProductEntity productEntity : productRepository.findAll()) {
            getAllProductsResponseDataList.add(new GetAllProductsResponseData(productEntity));
        }
        return getAllProductsResponseDataList;
    }

    @Override
    public ProductResponseData getByPid(Integer pid) {
        try {
//            ProductEntity productEntity = getEntityByPid(pid);
//
//            productRepository.getByPid(pid);
//            return new ProductResponseData(productEntity);
            return new ProductResponseData((getEntityByPid(pid)));

        } catch (Exception ex) {
            logger.warn("Get Product By Pid Failed: " + ex.getMessage());
            throw ex;
        }
    }


    public boolean isExistsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findByPid(pid);
        if (optionalProductEntity.isEmpty()) {
            throw new ProductNotFoundException(pid);
        }
        return optionalProductEntity.get();

//        return productRepository.findByPid(pid).orElseThrow(
//                () -> new ProductNotFoundException(pid)
//        );
    }
}

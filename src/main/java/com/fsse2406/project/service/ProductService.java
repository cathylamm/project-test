package com.fsse2406.project.service;

import com.fsse2406.project.data.product.domainObject.request.CreateProductRequestData;
import com.fsse2406.project.data.product.domainObject.response.CreateProductResponseData;
import com.fsse2406.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.entity.ProductEntity;

import java.util.List;


public interface ProductService {
    CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData);

    List<GetAllProductsResponseData> getAllProducts();

    ProductResponseData getByPid(Integer Pid);

    ProductEntity getEntityByPid(Integer pid);


}

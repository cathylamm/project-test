package com.fsse2406.project.api;

import com.fsse2406.project.data.product.domainObject.request.CreateProductRequestData;
import com.fsse2406.project.data.product.domainObject.response.CreateProductResponseData;
import com.fsse2406.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.fsse2406.project.data.product.domainObject.response.ProductResponseData;
import com.fsse2406.project.data.product.dto.request.CreateProductRequestDto;
import com.fsse2406.project.data.product.dto.response.CreateProductResponseDto;
import com.fsse2406.project.data.product.dto.response.GetAllProductsResponseDto;
import com.fsse2406.project.data.product.dto.response.ProductResponseDto;
import com.fsse2406.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        CreateProductRequestData createProductRequestData = new CreateProductRequestData(createProductRequestDto);
        CreateProductResponseData createProductResponseData = productService.createProduct(createProductRequestData);
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto(createProductResponseData);
        return createProductResponseDto;
    }

    @GetMapping
    public List<GetAllProductsResponseDto>getAllProducts(){
        List<GetAllProductsResponseData> getAllProductsResponseDataList = productService.getAllProducts();

        List<GetAllProductsResponseDto>getAllProductsResponseDtoList = new ArrayList<>();
        for(GetAllProductsResponseData getAllProductsResponseData : getAllProductsResponseDataList){
            GetAllProductsResponseDto getAllProductsResponseDto = new GetAllProductsResponseDto(getAllProductsResponseData);
            getAllProductsResponseDtoList.add(getAllProductsResponseDto);
        }
        return getAllProductsResponseDtoList;
    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable Integer pid){
//        ProductResponseData productResponseData = productService.getProductByPid(pid);
//        ProductResponseDto productResponseDto = new ProductResponseDto(productResponseData);
//        return productResponseDto;
        return new ProductResponseDto(
                productService.getByPid(pid)
        );
    }
}

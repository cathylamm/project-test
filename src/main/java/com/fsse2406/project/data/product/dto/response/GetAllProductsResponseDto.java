package com.fsse2406.project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2406.project.data.product.domainObject.response.GetAllProductsResponseData;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class GetAllProductsResponseDto {
    private Integer pid;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private boolean hasStock;

    public GetAllProductsResponseDto(GetAllProductsResponseData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.hasStock = data.getStock() > 0;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }
}

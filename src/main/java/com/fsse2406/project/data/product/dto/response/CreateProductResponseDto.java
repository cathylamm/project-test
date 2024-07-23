package com.fsse2406.project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2406.project.data.product.domainObject.response.CreateProductResponseData;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateProductResponseDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @Positive
    private BigDecimal price;

    @JsonProperty("stock")
    private Integer stock;

    public CreateProductResponseDto(CreateProductResponseData data){
        this.pid = data.getPid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imageUrl = data.getImageUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

package edu.innotech.dto;

public interface ProductDto {
    Long getId();
    String getAccount();
    Double getAmount();
    ProductTypeDto getProductTypeId();
    UserDto getUserId();
}

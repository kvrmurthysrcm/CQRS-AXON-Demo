package com.cqrs.demo.command.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductModel {

//    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

}
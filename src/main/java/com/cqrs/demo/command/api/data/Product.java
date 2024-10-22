package com.cqrs.demo.command.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    private String productId;

    private String name;
    private BigDecimal price;
    private Integer quantity;

}

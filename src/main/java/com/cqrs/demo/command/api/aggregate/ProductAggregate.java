package com.cqrs.demo.command.api.aggregate;

import com.cqrs.demo.command.api.commands.CreateProductCommand;
import com.cqrs.demo.command.api.events.ProductCreatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate

public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        System.out.println("@ProductAggregate() : createProductCommand= " + createProductCommand);
        // validations
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent ();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
        System.out.println("############# @ProductAggregate() created....");
    }

    public ProductAggregate() {

    }

    public void on(ProductCreatedEvent productCreatedEvent){
        System.out.println("############# @on():: productCreatedEvent = " + productCreatedEvent);
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
    }

}
package com.cqrs.demo.command.api.events;

import com.cqrs.demo.command.api.data.Product;
import com.cqrs.demo.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        System.out.println("############### @ProductEventsHandler :: on() : request to save event= " + event);
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
        System.out.println("@ProductEventsHandler :: on() : saved event= ");
    }
}
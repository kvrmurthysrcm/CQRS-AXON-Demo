package com.cqrs.demo.command.api.controller;

import com.cqrs.demo.command.api.commands.CreateProductCommand;
import com.cqrs.demo.command.api.model.ProductModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    // AXON Gateway
    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductModel productModel){
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                         .productId(UUID.randomUUID().toString())
                        //.productId("1")
                        .name(productModel.getName())
                        .price(productModel.getPrice())
                        .quantity(productModel.getQuantity())
                        .build();
        System.out.println("############# @addProduct():: createProductCommand = " + createProductCommand);
        String result = commandGateway.sendAndWait(createProductCommand);

        return result;
    }

}
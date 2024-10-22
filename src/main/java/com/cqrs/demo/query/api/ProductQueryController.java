package com.cqrs.demo.query.api;

import com.cqrs.demo.command.api.model.ProductModel;
import com.cqrs.demo.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    // AXON Gateway
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductModel> getProducts(){
        System.out.println("#################### Inside getProducts().....");
        GetProductsQuery getProductsQuery =
                new GetProductsQuery();
        System.out.println("#################### Inside getProducts():: queryGateway = " + queryGateway);

        List<ProductModel>  productModels =
        queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductModel.class))
                .join();

        return productModels;
    }

}
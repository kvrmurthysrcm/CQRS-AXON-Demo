package com.cqrs.demo.query.api.projection;

import com.cqrs.demo.command.api.data.Product;
import com.cqrs.demo.command.api.data.ProductRepository;
import com.cqrs.demo.command.api.model.ProductModel;
import com.cqrs.demo.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductModel> handle(GetProductsQuery getProductsQuery) {
        System.out.println("#################### Inside handle():: getProductsQuery = " + getProductsQuery);
        List<Product> products =
                productRepository.findAll();
        System.out.println("#################### Inside handle():: products = " + products);
        List<ProductModel> productModels =
                products.stream()
                        .map(product -> ProductModel
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());
        return productModels;
    }
}
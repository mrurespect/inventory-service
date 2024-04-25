package fsts.mrurespect.inventoryservice.web;

import fsts.mrurespect.inventoryservice.entity.Product;
import fsts.mrurespect.inventoryservice.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/graphql")
public class ProductGraphQlController {
    private final ProductRepository productRepository;

    public ProductGraphQlController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
}

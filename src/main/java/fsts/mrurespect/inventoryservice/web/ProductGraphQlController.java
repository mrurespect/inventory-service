package fsts.mrurespect.inventoryservice.web;

import fsts.mrurespect.inventoryservice.entity.Category;
import fsts.mrurespect.inventoryservice.entity.Product;
import fsts.mrurespect.inventoryservice.repository.CategoryRepository;
import fsts.mrurespect.inventoryservice.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/graphql")
public class ProductGraphQlController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductGraphQlController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument  Long id){
        return productRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Product not found"));
    }
    @QueryMapping
    public List<Category> categories(){
        return categoryRepository.findAll();
    }
    @QueryMapping
    public Category categoryById(@Argument Long id){
        return categoryRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Category not found"));
    }
    @MutationMapping
    public Product createProduct(@Argument Product product){
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(()-> new RuntimeException("Category not found"));//
        product.setCategory(category);//
        return productRepository.save(product);
    }
    @MutationMapping
    public String deleteProduct(@Argument Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        productRepository.delete(product);
        return "product deleted successfully";
    }
    @MutationMapping
    public Category createCategory(@Argument Category category){
        category.setId(null);
        if (category.getName().isEmpty()){
            throw new RuntimeException("Category name cannot be empty");
        }
        return categoryRepository.save(category);
    }
}

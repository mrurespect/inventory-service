package fsts.mrurespect.inventoryservice;

import fsts.mrurespect.inventoryservice.entity.Category;
import fsts.mrurespect.inventoryservice.entity.Product;
import fsts.mrurespect.inventoryservice.repository.CategoryRepository;
import fsts.mrurespect.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository,
                            CategoryRepository categoryRepository,
                            RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
    return args -> {
        List.of("C1 Ordinateurs", "C2 Imprimantes", "C3 Smartphone").forEach(c -> {
            categoryRepository.save(new Category(null, c, null));
        });
        categoryRepository.findAll().forEach(category -> {
            for (int i = 1; i <= 10; i++) {
                Product product =new Product(null,"Product " + i, i * 10.0, category);
                productRepository.save(product);
            }
        });
    };
    }
}



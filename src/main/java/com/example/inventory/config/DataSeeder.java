package com.example.inventory.config;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(CategoryRepository categoryRepository, SupplierRepository supplierRepository, ProductRepository productRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("Electronics", "Electronic devices and accessories"));
                categoryRepository.save(new Category("Office Supplies", "Office consumables and tools"));
                categoryRepository.save(new Category("Hardware", "Tools and hardware items"));
            }
            if (supplierRepository.count() == 0) {
                supplierRepository.save(new Supplier("ABC Trading", "sales@abctrading.test", "0917-000-0001", "Cavite, Philippines"));
                supplierRepository.save(new Supplier("Metro Supplies", "orders@metrosupplies.test", "0917-000-0002", "Metro Manila, Philippines"));
            }
            if (productRepository.count() == 0) {
                Product p1 = new Product();
                p1.setSku("USB-CABLE-001");
                p1.setName("USB-C Cable");
                p1.setDescription("1 meter charging/data cable");
                p1.setQuantity(50);
                p1.setReorderLevel(10);
                p1.setUnitPrice(new BigDecimal("120.00"));
                p1.setCategory(categoryRepository.findAll().get(0));
                p1.setSupplier(supplierRepository.findAll().get(0));
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setSku("BOND-PAPER-A4");
                p2.setName("A4 Bond Paper");
                p2.setDescription("Ream of 500 sheets");
                p2.setQuantity(7);
                p2.setReorderLevel(10);
                p2.setUnitPrice(new BigDecimal("260.00"));
                p2.setCategory(categoryRepository.findAll().get(1));
                p2.setSupplier(supplierRepository.findAll().get(1));
                productRepository.save(p2);
            }
        };
    }
}

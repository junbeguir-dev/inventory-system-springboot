package com.example.inventory.service;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.Supplier;
import com.example.inventory.repository.CategoryRepository;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class InventoryService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public InventoryService(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> listProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAllWithDetails();
        }
        return productRepository.search(keyword.trim());
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product saveProduct(Product product) {
        trimProduct(product);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void adjustStock(Long productId, int change) {
        Product product = getProduct(productId);
        int newQuantity = product.getQuantity() + change;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock cannot go below zero");
        }
        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> lowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    @Transactional(readOnly = true)
    public long productCount() {
        return productRepository.count();
    }

    @Transactional(readOnly = true)
    public long totalQuantity() {
        Long total = productRepository.totalQuantity();
        return total == null ? 0 : total;
    }

    @Transactional(readOnly = true)
    public BigDecimal totalInventoryValue() {
        BigDecimal total = productRepository.totalInventoryValue();
        return total == null ? BigDecimal.ZERO : total;
    }

    @Transactional(readOnly = true)
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Supplier> suppliers() {
        return supplierRepository.findAll();
    }

    public Category saveCategory(Category category) {
        category.setName(category.getName().trim());
        return categoryRepository.save(category);
    }

    public Supplier saveSupplier(Supplier supplier) {
        supplier.setName(supplier.getName().trim());
        return supplierRepository.save(supplier);
    }

    private void trimProduct(Product product) {
        product.setSku(product.getSku().trim().toUpperCase());
        product.setName(product.getName().trim());
    }
}

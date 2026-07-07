package com.example.inventory.repository;

import com.example.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySkuIgnoreCase(String sku);

    @Query("""
        select p from Product p
        left join fetch p.category
        left join fetch p.supplier
        where lower(p.name) like lower(concat('%', :keyword, '%'))
           or lower(p.sku) like lower(concat('%', :keyword, '%'))
           or lower(coalesce(p.description, '')) like lower(concat('%', :keyword, '%'))
        order by p.name
    """)
    List<Product> search(@Param("keyword") String keyword);

    @Query("select p from Product p left join fetch p.category left join fetch p.supplier order by p.name")
    List<Product> findAllWithDetails();

    @Query("select p from Product p left join fetch p.category left join fetch p.supplier where p.quantity <= p.reorderLevel order by p.quantity asc")
    List<Product> findLowStockProducts();

    @Query("select coalesce(sum(p.quantity), 0) from Product p")
    Long totalQuantity();

    @Query("select coalesce(sum(p.quantity * p.unitPrice), 0) from Product p")
    BigDecimal totalInventoryValue();
}

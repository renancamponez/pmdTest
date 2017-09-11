package com.avenuecode.orders.repository;

import com.avenuecode.orders.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
    @Query("SELECT t FROM Product t where t.price >= 30")
    List<Product> getExpensiveProducts();
}

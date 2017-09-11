package com.avenuecode.orders.repository;

import com.avenuecode.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {
    @Query("SELECT t FROM Order t where t.status = 'SHIPPED'")
	List<Order> getShipped();

    @Query("SELECT t FROM Order t where t.discount IS NOT NULL")
	List<Order> getDiscounted();

    @Query("SELECT t FROM Order t WHERE SIZE(t.products) >= 2")
	List<Order> getBigOrders();
}

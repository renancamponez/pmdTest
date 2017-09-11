package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) {
        return orderRepository.findOne(orderId);
    }

    // criteria 1
    public List<Order> getShipped() {
	return orderRepository.getShipped();
    }

    // criteria 2
    public List<Order> getDiscounted() {
	return orderRepository.getDiscounted();
    }

    // criteria 3
    public List<Order> getBigOrders() {
	return orderRepository.getBigOrders();
    }
}

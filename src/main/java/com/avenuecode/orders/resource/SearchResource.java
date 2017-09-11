package com.avenuecode.orders.resource;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.service.OrderService;
import com.avenuecode.orders.domain.Product;
import com.avenuecode.orders.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/search")
public class SearchResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ok(orderService.listOrders());
    }

    @GetMapping(value = "/1")
    public ResponseEntity<List<Order>> criteria1() {
        return ok(orderService.getShipped());
    }

    @GetMapping(value = "/2")
    public ResponseEntity<List<Order>> criteria2() {
        return ok(orderService.getDiscounted());
    }

    @GetMapping(value = "/3")
    public ResponseEntity<List<Order>> criteria3() {
        return ok(orderService.getBigOrders());
    }

    @GetMapping(value = "/4")
    public ResponseEntity<List<Product>> criteria4() {
        return ok(productService.getExpensiveProducts());
    }
}

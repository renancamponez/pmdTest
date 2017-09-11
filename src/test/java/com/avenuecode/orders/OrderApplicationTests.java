package com.avenuecode.orders;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.domain.Product;
import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.repository.ProductRepository;

import java.util.* ;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderApplicationTests {
    // @Autowired
    // private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    Product product1 = new Product("1", "1257833283", "9394550220002", "Diva Jeans", BigDecimal.valueOf(39.99));
    Product product2 = new Product("2", "1358743283", "7394650110003", "Polo Shirt", BigDecimal.valueOf(19.99));
    Product product3 = new Product("3", "1458843283", "7394750120000", "Floral Swing Skirt", BigDecimal.valueOf(69.99));
    Product product4 = new Product("4", "1358753283", "7394850130001", "Denim Short", BigDecimal.valueOf(29.99));
    Product product5 = new Product("5", "1258793283", "7394950140000", "True Skinny Jeans", BigDecimal.valueOf(49.99));

    List<Product> products1 = new ArrayList<Product>(Arrays.asList(product1, product2));
    List<Product> products2 = new ArrayList<Product>(Arrays.asList(product2));
    List<Product> products3 = new ArrayList<Product>(Arrays.asList(product2, product3, product5));
    List<Product> products4 = new ArrayList<Product>(Arrays.asList(product1, product3));
    List<Product> products5 = new ArrayList<Product>(Arrays.asList(product5));

    /*
    insert into order_product (order_id, product_id) values ('1', '1');
    insert into order_product (order_id, product_id) values ('1', '2');
    insert into order_product (order_id, product_id) values ('2', '2');
    insert into order_product (order_id, product_id) values ('3', '2');
    insert into order_product (order_id, product_id) values ('3', '3');
    insert into order_product (order_id, product_id) values ('3', '5');
    insert into order_product (order_id, product_id) values ('4', '1');
    insert into order_product (order_id, product_id) values ('4', '3');
    insert into order_product (order_id, product_id) values ('5', '5');
    */

    Order order1 = new Order("1", "RTL_1001", BigDecimal.valueOf(0), BigDecimal.valueOf(10), "SHIPPED", products1);  // dis, tax
    Order order2 = new Order("2", "RTL_1002", BigDecimal.valueOf(15.55), BigDecimal.valueOf(10), "FULFILLED", products2);
    Order order3 = new Order("3", "RTL_1003", BigDecimal.valueOf(19.99), BigDecimal.valueOf(8.5), "SHIPPED", products3);
    Order order4 = new Order("4", "RTL_1004", BigDecimal.valueOf(0), BigDecimal.valueOf(10), "SHIPPED", products4);
    Order order5 = new Order("5", "RTL_1005", BigDecimal.valueOf(0), BigDecimal.valueOf(9.5), "FULFILLED", products5);

    /*
    insert into orders (order_id, order_number, tax_percent, status) values ('1', 'RTL_1001', 10, 'SHIPPED');
    insert into orders (order_id, order_number, discount, tax_percent, status) values ('2', 'RTL_1002', 15.55, 10, 'FULFILLED');
    insert into orders (order_id, order_number, discount, tax_percent, status) values ('3', 'RTL_1003', 19.99, 8.5, 'SHIPPED');
    insert into orders (order_id, order_number, tax_percent, status) values ('4', 'RTL_1004', 10, 'SHIPPED');
    insert into orders (order_id, order_number, tax_percent, status) values ('5', 'RTL_1005', 9.5, 'FULFILLED');
    */


    @Before 
    public void initialize() {
	orderRepository.save(order1);
	orderRepository.save(order2);
	orderRepository.save(order3);
	orderRepository.save(order4);
	orderRepository.save(order5);

	productRepository.save(product1);
	productRepository.save(product2);
	productRepository.save(product3);
	productRepository.save(product4);
	productRepository.save(product5);
    }

    @Test
    public void test1() {
	List<Order> orders = orderRepository.getShipped(); 
	String result = orders.toString();

        // entityManager.persist();
	String expected = "[Order(orderId=3, orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]), Order(orderId=1, orderNumber=RTL_1001, discount=0, taxPercent=10, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=4, orderNumber=RTL_1004, discount=0, taxPercent=10, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99)])]";
	assertEquals(result, expected);
    }

    @Test
    public void test2() {
	List<Order> orders = orderRepository.getDiscounted();
	String result = orders.toString();

	String expected = "[Order(orderId=2, orderNumber=RTL_1002, discount=15.55, taxPercent=10, total=19.99, totalTax=1.99, grandTotal=6.43, status=FULFILLED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=3, orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]), Order(orderId=1, orderNumber=RTL_1001, discount=0, taxPercent=10, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=4, orderNumber=RTL_1004, discount=0, taxPercent=10, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99)]), Order(orderId=5, orderNumber=RTL_1005, discount=0, taxPercent=9.5, total=49.99, totalTax=4.74, grandTotal=54.73, status=FULFILLED, products=[Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)])]";
	assertEquals(result, expected);
    }

    @Test
    public void test3() {
        List<Order> orders = orderRepository.getBigOrders();
        String result = orders.toString();

        String expected ="[Order(orderId=3, orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]), Order(orderId=1, orderNumber=RTL_1001, discount=0, taxPercent=10, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=4, orderNumber=RTL_1004, discount=0, taxPercent=10, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99)])]";
	assertEquals(result, expected);
    }

    @Test
    public void test4() {
	List<Product> products = productRepository.getExpensiveProducts();
	String result = products.toString();

	String expected ="[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]";
	assertEquals(result, expected);
    }

    @Test
    public void testListOrders() {
	List<Order> orders = orderRepository.getDiscounted();
	String result = orders.toString();

	String expected ="[Order(orderId=2, orderNumber=RTL_1002, discount=15.55, taxPercent=10, total=19.99, totalTax=1.99, grandTotal=6.43, status=FULFILLED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=3, orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]), Order(orderId=1, orderNumber=RTL_1001, discount=0, taxPercent=10, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)]), Order(orderId=4, orderNumber=RTL_1004, discount=0, taxPercent=10, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99)]), Order(orderId=5, orderNumber=RTL_1005, discount=0, taxPercent=9.5, total=49.99, totalTax=4.74, grandTotal=54.73, status=FULFILLED, products=[Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)])]";
        assertEquals(result, expected);
    }

    @Test
    public void testGetOrder() {
	Order order = orderRepository.findOne("1");
	String result = order.toString();

        String expected ="Order(orderId=1, orderNumber=RTL_1001, discount=0, taxPercent=10, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)])";
        assertEquals(result, expected);
    }

    @Test
    public void testListProducts() {
	List<Product> products = productRepository.findAll();
	String result = products.toString();

	String expected ="[Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=2, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99), Product(productId=3, upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99), Product(productId=4, upc=1358753283, sku=7394850130001, description=Denim Short, price=29.99), Product(productId=5, upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99)]";
	assertEquals(result, expected);
    }

    @Test
    public void testGetProduct() {
	Product product = productRepository.findOne("1");
	String result = product.toString();

	String expected ="Product(productId=1, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99)";
        assertEquals(result, expected);
    }
}



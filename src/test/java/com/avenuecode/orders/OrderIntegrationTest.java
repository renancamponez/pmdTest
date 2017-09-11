package com.avenuecode.orders;

import java.util.* ;
import static org.junit.Assert.assertEquals;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrdersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // test existing endpoints
    @Test
    public void testOrders() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/orders", List.class);
        List objects = responseEntity.getBody();
        String result = objects.toString();
        String expected = "[{orderNumber=RTL_1001, taxPercent=10.0, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}]}, {orderNumber=RTL_1002, discount=15.55, taxPercent=10.0, total=19.99, totalTax=1.99, grandTotal=6.43, status=FULFILLED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}]}, {orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}, {upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]}, {orderNumber=RTL_1004, taxPercent=10.0, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}]}, {orderNumber=RTL_1005, taxPercent=9.5, total=49.99, totalTax=4.74, grandTotal=54.73, status=FULFILLED, products=[{upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]}]";
        assertEquals(result, expected);
    }

    @Test
    public void testOrderDetails() {
        ResponseEntity<Order> responseEntity = restTemplate.getForEntity("/orders/1", Order.class);
        Order order = responseEntity.getBody();
        String result = order.toString();
        String expected = "Order(orderId=null, orderNumber=RTL_1001, discount=null, taxPercent=10.00, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=null, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=null, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)])";
        assertEquals(result, expected);
    }

    @Test
    public void testProductDetails() {
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("/products/1", Product.class);
        Product product = responseEntity.getBody();
        String result = product.toString();
        String expected = "Product(productId=null, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99)";
        assertEquals(result, expected);
    }

    // test new endpoints
    @Test
    public void test1() {
	ResponseEntity<List> responseEntity = restTemplate.getForEntity("/search/1", List.class);
	List objects = responseEntity.getBody();
	// MediaType contentType = responseEntity.getHeaders().getContentType();
	// HttpStatus statusCode = responseEntity.getStatusCode();
	// System.out.println(result);
	String result = objects.toString();
	String expected = "[{orderNumber=RTL_1001, taxPercent=10.0, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}]}, {orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}, {upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]}, {orderNumber=RTL_1004, taxPercent=10.0, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}]}]";
	assertEquals(result, expected);
    }

    @Test
    public void test2() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/search/2", List.class);
        List objects = responseEntity.getBody();
        String result = objects.toString();
        String expected = "[{orderNumber=RTL_1002, discount=15.55, taxPercent=10.0, total=19.99, totalTax=1.99, grandTotal=6.43, status=FULFILLED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}]}, {orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}, {upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]}]";
        assertEquals(result, expected);
    }

    @Test
    public void test3() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/search/3", List.class);
        List objects = responseEntity.getBody();
        String result = objects.toString();
        String expected = "[{orderNumber=RTL_1001, taxPercent=10.0, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}]}, {orderNumber=RTL_1003, discount=19.99, taxPercent=8.5, total=139.97, totalTax=11.89, grandTotal=131.87, status=SHIPPED, products=[{upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99, prodId=2}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}, {upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]}, {orderNumber=RTL_1004, taxPercent=10.0, total=109.98, totalTax=10.99, grandTotal=120.97, status=SHIPPED, products=[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}]}]";
        assertEquals(result, expected);
    }

    @Test
    public void test4() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/search/4", List.class);
        List objects = responseEntity.getBody();
        String result = objects.toString();
        String expected = "[{upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99, prodId=1}, {upc=1458843283, sku=7394750120000, description=Floral Swing Skirt, price=69.99, prodId=3}, {upc=1258793283, sku=7394950140000, description=True Skinny Jeans, price=49.99, prodId=5}]";
        assertEquals(result, expected);
    }
}

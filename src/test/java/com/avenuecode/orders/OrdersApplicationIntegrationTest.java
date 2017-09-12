package com.avenuecode.orders;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OrdersApplication.class)
@AutoConfigureMockMvc
public class OrdersApplicationIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testAllOrders() throws Exception {
		mvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));
	}

	@Test
	public void testFindOne() throws Exception {
		ResponseEntity<Order> responseEntity = restTemplate.getForEntity("/orders/1", Order.class);
		Order order = responseEntity.getBody();
		String result = order.toString();
		String expected = "Order(orderId=null, orderNumber=RTL_1001, discount=null, taxPercent=10.00, total=59.98, totalTax=5.99, grandTotal=65.97, status=SHIPPED, products=[Product(productId=null, upc=1257833283, sku=9394550220002, description=Diva Jeans, price=39.99), Product(productId=null, upc=1358743283, sku=7394650110003, description=Polo Shirt, price=19.99)])";
		assertEquals(result, expected);

	}

}
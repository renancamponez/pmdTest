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

	@Test
	public void testAllOrders() throws Exception {
		mvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));
	}

	@Test
	public void testFindOne() throws Exception {
		mvc.perform(get("/orders/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testFindNonExisting() throws Exception {
		mvc.perform(get("/orders/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
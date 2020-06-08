package com.bfxy.springboot;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.producer.RabbitOrderSender;
import com.bfxy.springboot.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private RabbitOrderSender rabbitOrderSender;
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void testSender() throws Exception {
		Order order = new Order();
		order.setId("2020030500000001");
		order.setName("测试订单");
		order.setMessageId(System.currentTimeMillis() + "$" +UUID.randomUUID().toString());
		rabbitOrderSender.sendOrder(order);
	}
	
	@Test
	public void testCreateOrder() throws Exception {
		Order order = new Order();
		order.setId("2020030500000001");
		order.setName("测试订单");
		order.setMessageId(System.currentTimeMillis() + "$" +UUID.randomUUID().toString());
		orderService.createOrder(order);
	}
}

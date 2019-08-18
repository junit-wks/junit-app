package com.capgemini.junit.wks.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.junit.wks.config.AppConfig;
import com.capgemini.junit.wks.document.Item;
import com.capgemini.junit.wks.document.Order;
import com.capgemini.junit.wks.document.OrderType;
import com.capgemini.junit.wks.repository.OrderRepository;
import com.capgemini.junit.wks.service.impl.OrderServiceImpl;
import com.capgemini.junit.wks.util.MathHelper;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes={AppConfig.class})
public class OrderServiceTest {
	
	private static final String CAPGEMINI = "Capgemini";
	private static final String SD_UND_M = "sd&m";
	private static final String MOUSE = "Myszka";
	private static final String KEYBOARD = "Klawiatura";
	
	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
    private OrderService orderService = new OrderServiceImpl();
	
	@Test
	public void testGetListOfAllCustomers() {
		//Given
		Order order1 = createOrder(CAPGEMINI, OrderType.PURCHASED, new ArrayList<>());
		Order order2 = createOrder(SD_UND_M, OrderType.PURCHASED, new ArrayList<>());
		
		List<Order> orders = Arrays.asList(order1, order2);
		
		doReturn(orders).when(orderRepository).findAll();
		
		//When
		List<String> result = orderService.getListOfAllCustomers();
		
		//Then
		assertThat(result)
			.contains(CAPGEMINI, SD_UND_M)
			.hasSize(2);
	}
	
	@Test
	public void testGetListOfAllCustomersItems() {
		//Given
		Item item1 = createItem(MathHelper.getDecimal("20.22"), 2, MOUSE);
		Item item2 = createItem(MathHelper.getDecimal("10.57"), 10, KEYBOARD);	
		Order order1 = createOrder(CAPGEMINI, OrderType.PURCHASED, Arrays.asList(item1, item2));
		
		Item item3 = createItem(MathHelper.getDecimal("20.22"), 2, MOUSE);
		Order order2 = createOrder(CAPGEMINI, OrderType.PURCHASED, Arrays.asList(item3));
		
		List<Order> orders = Arrays.asList(order1, order2);
		
		doReturn(orders).when(orderRepository).findByCustomer(CAPGEMINI);
		
		//When
		List<Item> result = orderService.getListOfAllCustomersItems(CAPGEMINI);
		
		//Then
		assertThat(result)
			.contains(item1, item2, item3)
			.hasSize(3);
	}
	
	@Test
	public void testGetListOfAllCustomersItemsWhenCustomerDoesntExist() {
		doReturn(new ArrayList<Order>()).when(orderRepository).findByCustomer(CAPGEMINI);
		
		List<Item> result = orderService.getListOfAllCustomersItems(CAPGEMINI);

		assertThat(result).isEmpty();
	}
	
	@Test
	public void testDeleteCancelledOrders() {
		//Given
		List<Order> orders = Arrays.asList(new Order(), new Order());
		
		doReturn(orders).when(orderRepository).findByType(OrderType.CANCELLED);
		
		//When
		orderService.deleteCancelledOrders();
		
		//Then
		verify(orderRepository).deleteAll(orders);
		verifyNoMoreInteractions(orderRepository);
	}
	
	private Order createOrder(String customer, OrderType orderType, List<Item> items) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setType(orderType);
		order.setItems(items);
		return order;
	}
	
	private Item createItem(BigDecimal price, int quantity, String product) {
		Item item = new Item();
		item.setPrice(price);
		item.setQuantity(quantity);
		item.setProduct(product);
		return item;
	}
}

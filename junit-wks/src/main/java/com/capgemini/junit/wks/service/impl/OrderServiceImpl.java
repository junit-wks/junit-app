package com.capgemini.junit.wks.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.junit.wks.document.Item;
import com.capgemini.junit.wks.document.Order;
import com.capgemini.junit.wks.document.OrderType;
import com.capgemini.junit.wks.repository.OrderRepository;
import com.capgemini.junit.wks.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
    private OrderRepository orderRepository;
	
	@Override
	public List<String> getListOfAllCustomers() {
		return orderRepository.findAll().stream().map(o -> o.getCustomer()).collect(Collectors.toList());
	}
	
	@Override
	public List<Item> getListOfAllCustomersItems(String customer) {
		List<Order> orders = orderRepository.findByCustomer(customer);
		
		return orders.stream().map(Order::getItems).flatMap(Collection::stream).collect(Collectors.toList());
	}
	
	@Override
	public void deleteCancelledOrders() {
		List<Order> orders = orderRepository.findByType(OrderType.CANCELLED);
		
		orderRepository.deleteAll(orders);
	}
	
	@Override
	public BigDecimal calculateTotalPrice(Order order) {
		throw new UnsupportedOperationException("Ćwiczenie TDD");
	}
	
}

package com.capgemini.junit.wks.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.junit.wks.document.Item;
import com.capgemini.junit.wks.document.Order;

@Service
public interface OrderService {

	List<String> getListOfAllCustomers();
	
	List<Item> getListOfAllCustomersItems(String customer);
	
	BigDecimal calculateTotalPrice(Order order);
	
	void deleteCancelledOrders();
}

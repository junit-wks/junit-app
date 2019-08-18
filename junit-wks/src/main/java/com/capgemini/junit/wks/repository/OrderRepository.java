package com.capgemini.junit.wks.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.junit.wks.document.Order;
import com.capgemini.junit.wks.document.OrderType;

public interface OrderRepository extends MongoRepository<Order, String> {
	
	List<Order> findByCustomer(String customer);
	
	List<Order> findByCustomerLike(String customer);

	List<Order> findByCustomerAndType(String customer, OrderType type);

	List<Order> findByType(OrderType type);

}

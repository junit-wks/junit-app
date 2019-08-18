package com.capgemini.junit.wks.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.junit.wks.config.MongoJUnitConfig;
import com.capgemini.junit.wks.document.Order;
import com.capgemini.junit.wks.document.OrderType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MongoJUnitConfig.class})
public class OrderRepositoryTest {

	private static final String CAPGEMINI = "Capgemini";

	private static final String SD_UND_M = "sd&m";

	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired 
	private MongoOperations mongoOps;
	
	@BeforeEach
    public void clearBefore() {
		mongoOps.dropCollection(Order.class);
    }
	
	@AfterEach
    public void clearAfter() {
		mongoOps.dropCollection(Order.class);
    }
	
	@Test
	public void testFindByCustomer() {
		// given
		createAndPersistOrder(CAPGEMINI, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.PURCHASED);
		
		// when
		List<Order> orders = orderRepository.findByCustomer(SD_UND_M);
		
		// then
		assertThat(orders)
			.allMatch(o -> o.getCustomer().equals(SD_UND_M))
			.hasSize(2);
	}
	
	@Test
	public void testFindByCustomerWhenCustomerDoesntExist() {
		// given
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		
		// when
		List<Order> orders = orderRepository.findByCustomer(CAPGEMINI);
		
		// then
		assertThat(orders).isEmpty();
	}
	
	@Test
	public void testFindByCustomerLike() {
		// given
		createAndPersistOrder(CAPGEMINI, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.PURCHASED);
		
		// when
		List<Order> orders = orderRepository.findByCustomerLike("sd");
		
		// then
		assertThat(orders)
			.allMatch(o -> o.getCustomer().equals(SD_UND_M))
			.hasSize(2);
	}
	
	@Test
	public void testFindByCustomerLikeWhenCustomerDoesntExist() {
		// given
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		
		// when
		List<Order> orders = orderRepository.findByCustomerLike(CAPGEMINI);
		
		// then
		assertThat(orders).isEmpty();
	}
	
	@Test
	public void testFindByCustomerAndType() {
		// given
		createAndPersistOrder(CAPGEMINI, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		createAndPersistOrder(SD_UND_M, OrderType.DELIVERED);
		createAndPersistOrder(SD_UND_M, OrderType.PURCHASED);
		createAndPersistOrder(SD_UND_M, OrderType.SEND);
		
		// when
		List<Order> orders = orderRepository.findByCustomerAndType(SD_UND_M, OrderType.CANCELLED);
		
		// then
		assertThat(orders)
			.allMatch(o -> o.getCustomer().equals(SD_UND_M) && o.getType().equals(OrderType.CANCELLED))
			.hasSize(1);
	}
	
	@Test
	public void testFindByCustomerAndTypeWhenCustomerAndTypeDontMatch() {
		// given
		createAndPersistOrder(SD_UND_M, OrderType.CANCELLED);
		createAndPersistOrder(CAPGEMINI, OrderType.DELIVERED);
		
		// when
		List<Order> orders = orderRepository.findByCustomerAndType(CAPGEMINI, OrderType.CANCELLED);
		
		// then
		assertThat(orders).isEmpty();
	}
	
	@Test
	public void testFindByType() {
		createAndPersistOrder(CAPGEMINI, OrderType.CANCELLED);
		createAndPersistOrder(CAPGEMINI, OrderType.DELIVERED);
		createAndPersistOrder(CAPGEMINI, OrderType.PURCHASED);
		createAndPersistOrder(CAPGEMINI, OrderType.SEND);
		
		// when
		List<Order> orders = orderRepository.findByType(OrderType.PURCHASED);
		
		// then
		assertThat(orders)
			.allMatch(o -> o.getType().equals(OrderType.PURCHASED))
			.hasSize(1);
	}
	
	@Test
	public void testFindByTypeWhenTypeDoesntExist() {
		// given
		createAndPersistOrder(CAPGEMINI, OrderType.CANCELLED);
		createAndPersistOrder(CAPGEMINI, OrderType.DELIVERED);
		createAndPersistOrder(CAPGEMINI, OrderType.SEND);
		
		// when
		List<Order> orders = orderRepository.findByType(OrderType.PURCHASED);
		
		// then
		assertThat(orders).isEmpty();
	}

	private void createAndPersistOrder(String customer, OrderType orderType) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setType(orderType);
		orderRepository.save(order);
	}
	
	
}

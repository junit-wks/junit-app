package com.capgemini.junit.wks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.capgemini.junit.wks.config.AppConfig;
import com.capgemini.junit.wks.document.Order;
import com.capgemini.junit.wks.repository.OrderRepository;
import com.capgemini.junit.wks.service.OrderService;

public class App {
	
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	try {
    		
    		OrderService orderService = ctx.getBean(OrderService.class);
    		
    		OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
    		
    		Order order = new Order();
    		order.setCustomer("OK");
    		orderRepository.save(order);
    		
    		System.out.println(orderService.getListOfAllCustomers());
    		
    		orderRepository.deleteAll();
    	} finally {
    	    ((AbstractApplicationContext) ctx).close();
    	}
    	

    }
}

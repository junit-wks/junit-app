package com.capgemini.junit.wks.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	
	@Id
	private String id;
	
	private String customer;
	
	private OrderType type;
	
	private List<Item> items = new ArrayList<Item>();

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public OrderType getType() {
		return type;
	}
	
	public void setType(OrderType type) {
		this.type = type;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getId() {
		return id;
	}
	
}

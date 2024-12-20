package com.quest.shoppingcart.service;

import java.util.Map;

public interface CustomerService {

	/**
	 * This method is used for fetching the orders by the customers
	 * 
	 * @param customerId - customerId
	 * @param page - page
	 * @param limit - limit
	 * @return - Map<String, Object> 
	 */
	public Map<String, Object> getOrdersByCustomerId(String customerId, String page, String limit);

}

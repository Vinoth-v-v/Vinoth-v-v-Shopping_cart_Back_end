package com.quest.shoppingcart.service;

import java.util.List;

import com.quest.shoppingcart.dto.OrderDTO;
import com.quest.shoppingcart.dto.OrderTrackerDTO;

public interface OrderService {

	public OrderDTO saveOrder(OrderDTO orderDTO);

	public OrderDTO getOrders(String orderId);

	public void updateStatus(OrderDTO orderDTO);

	public List<OrderTrackerDTO> getOrderUpdates(String orderId);

}

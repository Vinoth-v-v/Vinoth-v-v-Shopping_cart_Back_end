package com.quest.shoppingcart.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.quest.shoppingcart.dto.OrderDTO;
import com.quest.shoppingcart.entity.Order;

public class OrderMapper {

	public static OrderDTO toOrderDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setOrderStatus(order.getMasterOrderStatus().getOrderStatus());
		orderDTO.setOrderDetailsDTO(OrderDetailMapper.toOrderDetailDTOList(order.getOrderDetails()));
		orderDTO.setTotalPrice(order.getTotalPrice());
		return orderDTO;
	}

	public static List<OrderDTO> toOrderDTOList(List<Order> orders){
		return orders.stream()
		        .map(OrderMapper::toOrderDTO)
		        .collect(Collectors.toList());
	}

	public static Order toOrderEntity(OrderDTO orderDTO) {
		Order order = new Order();
		order.setAddressId(orderDTO.getAddressId());
		order.setCustomerId(orderDTO.getCustomerId());
		order.setOrderDetails(OrderDetailMapper.toOrderDetailEntityList(orderDTO.getOrderDetailsDTO()));
		order.setTotalPrice(orderDTO.getTotalPrice());
		order.setOrderStatusId(orderDTO.getOrderStatusId());
		return order;
	}

	public static OrderDTO saveOrderEntityToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
		orderDTO.setTotalPrice(order.getTotalPrice());
		return orderDTO;
	}

}

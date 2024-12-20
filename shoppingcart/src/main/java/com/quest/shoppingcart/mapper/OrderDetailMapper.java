package com.quest.shoppingcart.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.quest.shoppingcart.dto.OrderDetailDTO;
import com.quest.shoppingcart.entity.OrderDetail;

public class OrderDetailMapper {
	
	public static OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setProductId(orderDetail.getProductId());
		orderDetailDTO.setProductDescription(orderDetail.getProduct().getProductDescription());
		orderDetailDTO.setProductName(orderDetail.getProduct().getProductName());
		orderDetailDTO.setProductPrice(orderDetail.getProduct().getProductPrice());
		orderDetailDTO.setQuantity(orderDetail.getQuantity());
		return orderDetailDTO;
	}

	public static List<OrderDetailDTO> toOrderDetailDTOList(List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(OrderDetailMapper::toOrderDetailDTO).collect(Collectors.toList());
	}

	public static OrderDetail toOrderDetailEntity(OrderDetailDTO orderDetailDTO) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductId(orderDetailDTO.getProductId());
		orderDetail.setQuantity(orderDetailDTO.getQuantity());
		return orderDetail;
	}

	public static List<OrderDetail> toOrderDetailEntityList(List<OrderDetailDTO> orderDetailsDTO) {
		return orderDetailsDTO.stream().map(OrderDetailMapper::toOrderDetailEntity).collect(Collectors.toList());
	}
}

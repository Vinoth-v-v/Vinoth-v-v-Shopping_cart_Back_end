package com.quest.shoppingcart.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderDTO {

	private Integer orderId;

	private Integer customerId;

	private Integer addressId;

	private Integer orderStatusId;

	private String orderStatus;

	private List<OrderDetailDTO> orderDetailsDTO;

	private Timestamp expectedDeliveryDate;

	private Double totalPrice;

}

package com.quest.shoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderDetailDTO {

	private Integer productId;

	private String productName;

	private Double productPrice;

	private String productDescription;

	private Integer quantity;

}

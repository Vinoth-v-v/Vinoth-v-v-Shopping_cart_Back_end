package com.quest.shoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ProductDTO {

	private Integer productId;

	private String productName;

	private String productDescription;

	private Double productPrice;

	private Integer stock;

}

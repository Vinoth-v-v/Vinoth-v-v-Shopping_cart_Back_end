package com.quest.shoppingcart.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderTrackerDTO {

	private String orderStatus;

	private Timestamp updatedAt;

}

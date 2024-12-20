package com.quest.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.shoppingcart.dto.OrderDTO;
import com.quest.shoppingcart.dto.OrderTrackerDTO;
import com.quest.shoppingcart.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO) {
			return new ResponseEntity<>(orderService.saveOrder(orderDTO),HttpStatus.CREATED);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity <OrderDTO> getOrders(@PathVariable String orderId ) {
		return new ResponseEntity<>(orderService.getOrders(orderId),HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<String> updateStatus(@RequestBody OrderDTO orderDTO) {
		orderService.updateStatus(orderDTO);
		return new ResponseEntity<>("Order status has been changed ",HttpStatus.OK);
	}

	@GetMapping("{orderId}/order-updates") // Need to work on it
	public ResponseEntity<List<OrderTrackerDTO>>  getOrderUpdates(@PathVariable String orderId ) {
		return new ResponseEntity<>(orderService.getOrderUpdates(orderId),HttpStatus.OK);
	}
}

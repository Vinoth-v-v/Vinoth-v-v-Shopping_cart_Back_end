package com.quest.shoppingcart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.shoppingcart.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{customerId}/orders")
	public ResponseEntity<Map<String,Object>> getOrdersByCustomer(@PathVariable String customerId,
			@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "1") String limit) {
		return new ResponseEntity<>(customerService.getOrdersByCustomerId(customerId,page,limit),HttpStatus.OK);
	}
}

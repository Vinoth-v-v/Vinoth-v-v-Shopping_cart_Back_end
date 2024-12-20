package com.quest.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.shoppingcart.dto.ProductDTO;
import com.quest.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(required = false) int page ,@RequestParam(required = false) int limit){
		log.info("Entered inside getProducts method : {}");
		return  new ResponseEntity<>(productService.getProducts(page,limit),HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) {// need to make the paramter string
		log.info("Entered inside getProductById method : {}");
		return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
	}

	
}

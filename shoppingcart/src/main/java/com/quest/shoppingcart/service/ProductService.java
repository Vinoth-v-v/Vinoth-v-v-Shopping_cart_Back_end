package com.quest.shoppingcart.service;

import java.util.List;

import com.quest.shoppingcart.dto.ProductDTO;

public interface ProductService {

	public List<ProductDTO> getProducts(int page, int limit);

	public ProductDTO getProductById(Integer productId);
}

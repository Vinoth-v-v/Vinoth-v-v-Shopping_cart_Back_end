package com.quest.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.shoppingcart.constants.Constants;
import com.quest.shoppingcart.dto.ProductDTO;
import com.quest.shoppingcart.entity.Product;
import com.quest.shoppingcart.exception.ShoppingCartException;
import com.quest.shoppingcart.mapper.ProductMapper;
import com.quest.shoppingcart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getProducts(int page, int limit) {
		log.info("Entered inside getProductById: {}");
		try {
			PageRequest pageRequest = PageRequest.of(page, limit);
			 List<Product> products = productRepository.findAll(pageRequest).get().toList();
			return ProductMapper.toProductDTOList(products);
		} catch (Exception e) {
			log.error("Internal server error in  getProducts method: {} ", e);
			throw new ShoppingCartException();
		}
	}

	@Override
	public ProductDTO getProductById(Integer productId) {
		log.info("Entered inside getProductById: {}");
			Optional<Product> product = productRepository.findById(productId);
			if (product.isPresent()) {
				return ProductMapper.toProductDTO(product.get());
			} else { // Exception can't be generic.
				throw new ShoppingCartException("Invalid product id: " + productId,Constants.INVALID_INPUT_ERROR,HttpStatus.NOT_FOUND);
			}
	}

}

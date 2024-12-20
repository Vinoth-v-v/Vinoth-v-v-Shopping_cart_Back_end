package com.quest.shoppingcart.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.quest.shoppingcart.dto.ProductDTO;
import com.quest.shoppingcart.entity.Product;

public class ProductMapper {

	public static ProductDTO toProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setProductPrice(product.getProductPrice());
		productDTO.setStock(product.getStock());
		return productDTO;
	}

	public static List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }
}

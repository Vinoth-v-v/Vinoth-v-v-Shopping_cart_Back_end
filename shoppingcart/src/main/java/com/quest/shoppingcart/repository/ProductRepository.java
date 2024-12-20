package com.quest.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.shoppingcart.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE Product p set p.stock = (p.stock -:quantity),p.updatedAt = CURRENT_TIMESTAMP where p.productId=:productId")
	public void updateStock(@Param("quantity") Integer quantity, @Param("productId") Integer productId);

	@Query("SELECT SUM(p.productPrice) FROM Product p WHERE p.productId in (:productIds)")
	public Double getTotalProductPrice(@Param("productIds") List<Integer> productIds);
}

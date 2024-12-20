package com.quest.shoppingcart.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.shoppingcart.entity.Order;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Order o set o.orderStatusId=:orderStatusId, o.updatedAt = CURRENT_TIMESTAMP where o.orderId=:orderId")
	public void updateOrderStatus(@Param("orderId") Integer orderId, @Param("orderStatusId") Integer orderStatusId);

	@Query("FROM Order o WHERE o.customerId=:customerId ORDER BY o.createdAt DESC")
	public List<Order> getOrdersByCustomerId(@Param("customerId") Integer customerId, PageRequest pageRequest);

	@Query("SELECT COUNT(1) FROM Order o where o.customerId=:customerId")
	public Integer getTotalNoOfOrdersByCustomer(@Param("customerId") Integer customerId);

}

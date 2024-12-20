package com.quest.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.shoppingcart.dto.OrderTrackerDTO;
import com.quest.shoppingcart.entity.OrderTracker;

public interface OrderTrackerRepository extends JpaRepository<OrderTracker, Integer>{

	@Query("SELECT new com.quest.shoppingcart.dto.OrderTrackerDTO(mos.orderStatus,ot.updatedAt) FROM OrderTracker ot JOIN"
			+ " MasterOrderStatus mos ON ot.orderStatusId = mos.orderStatusId and ot.orderId=:orderId ORDER BY ot.updatedAt")
	public List<OrderTrackerDTO> getOrderUpdates(@Param("orderId") Integer orderId);

}

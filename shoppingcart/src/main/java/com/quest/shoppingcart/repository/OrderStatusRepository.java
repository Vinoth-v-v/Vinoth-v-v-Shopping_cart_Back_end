package com.quest.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.shoppingcart.entity.MasterOrderStatus;

public interface OrderStatusRepository extends JpaRepository<MasterOrderStatus, Integer>{

	@Query("SELECT mos.orderStatus FROM  MasterOrderStatus mos WHERE mos.orderStatusId=:orderStatusId")
	public String getOrderStatusbyId(@Param("orderStatusId")Integer orderStatusdId);

}

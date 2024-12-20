package com.quest.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.shoppingcart.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("SELECT c.customerName FROM Customer c where c.customerId=:customerId ")
	public String getCustomerName(@Param("customerId") Integer customerId);

}

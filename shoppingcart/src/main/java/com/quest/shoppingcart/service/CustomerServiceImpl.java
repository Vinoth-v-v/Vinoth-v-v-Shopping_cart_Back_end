package com.quest.shoppingcart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quest.shoppingcart.dto.OrderDTO;
import com.quest.shoppingcart.exception.ShoppingCartException;
import com.quest.shoppingcart.mapper.OrderMapper;
import com.quest.shoppingcart.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Map<String, Object> getOrdersByCustomerId(String customerId, String page, String limit) {
		try {
			Map<String, Object> orderListMap = new HashMap<>();
			Integer idOfCustomer = Integer.parseInt(customerId.trim());
			PageRequest pageRequest = PageRequest.of(Integer.parseInt(page.trim()), Integer.parseInt(limit.trim()));
			List<OrderDTO> orderDTOList = OrderMapper.toOrderDTOList(orderRepository.getOrdersByCustomerId(idOfCustomer, pageRequest));
			orderListMap.put("totalNoOfOrders", orderRepository.getTotalNoOfOrdersByCustomer(idOfCustomer));
			orderListMap.put("orders", orderDTOList);
			return orderListMap;
		} catch (DataAccessException e) { // Had doubt in this and it got cleared by Sumesh regarding loosing of real exception
			throw new ShoppingCartException();
		}
	}

}

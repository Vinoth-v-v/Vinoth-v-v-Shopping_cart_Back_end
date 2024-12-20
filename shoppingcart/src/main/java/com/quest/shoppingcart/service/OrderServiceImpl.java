package com.quest.shoppingcart.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.quest.shoppingcart.constants.Constants;
import com.quest.shoppingcart.dto.OrderDTO;
import com.quest.shoppingcart.dto.OrderDetailDTO;
import com.quest.shoppingcart.dto.OrderTrackerDTO;
import com.quest.shoppingcart.entity.Order;
import com.quest.shoppingcart.entity.OrderTracker;
import com.quest.shoppingcart.exception.OrderNotFoundException;
import com.quest.shoppingcart.exception.ProductNotFoundException;
import com.quest.shoppingcart.exception.ShoppingCartException;
import com.quest.shoppingcart.mapper.OrderMapper;
import com.quest.shoppingcart.repository.CustomerRepository;
import com.quest.shoppingcart.repository.OrderRepository;
import com.quest.shoppingcart.repository.OrderStatusRepository;
import com.quest.shoppingcart.repository.OrderTrackerRepository;
import com.quest.shoppingcart.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderStatusRepository orderStatusRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderTrackerRepository orderTrackerRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public OrderDTO saveOrder(OrderDTO orderDTO) {
		try {
			orderDTO.setOrderStatusId(Constants.PROCESSING);
			Order order = OrderMapper.toOrderEntity(orderDTO);
			List<OrderDetailDTO> orderDetailsDTO = orderDTO.getOrderDetailsDTO();
			String customerName = customerRepository.getCustomerName(orderDTO.getCustomerId()); //customer id is enough
			setOrderAndOrderDetails(orderDTO, order, customerName, orderDetailsDTO);
			OrderDTO saveOrderDTO = OrderMapper.saveOrderEntityToDTO(orderRepository.save(order));
			orderTrackerRepository.save(setOrderTrackerDetails(saveOrderDTO, customerName, Constants.PROCESSING));
			saveOrderDTO.setOrderStatus(orderStatusRepository.getOrderStatusbyId(Constants.PROCESSING));
			orderDetailsDTO.forEach(orderDetailDTO -> {
				productRepository.updateStock(orderDetailDTO.getQuantity(), orderDetailDTO.getProductId()); // Need to check on it multiple DB is happening here
			});
			return saveOrderDTO;
		}catch (DataIntegrityViolationException e) {
			
			throw new ProductNotFoundException(e,orderDTO.getOrderDetailsDTO().get(0).getProductId());
		}
		catch (DataAccessException e) { // Had doubt in this and it got cleared by Sumesh regarding loosing of real exception
			throw new ShoppingCartException();
		}
	}

	private OrderTracker setOrderTrackerDetails(OrderDTO orderDTO, String customerName, Integer orderStatusId) {
		OrderTracker orderTracker = new OrderTracker();
		orderTracker.setOrderId(orderDTO.getOrderId());
		orderTracker.setOrderStatusId(orderStatusId);
		orderTracker.setCreatedBy(customerName);
		orderTracker.setUpdatedBy(customerName);
		return orderTracker;
	}

	private void setOrderAndOrderDetails(OrderDTO orderDTO, Order order, String customerName,
			List<OrderDetailDTO> orderDetailsDTO) {
		List<Integer> productIds = orderDetailsDTO.stream().map(OrderDetailDTO :: getProductId).collect(Collectors.toList());
		Double totalProductPrice = productRepository.getTotalProductPrice(productIds);
		order.setTotalPrice(totalProductPrice);
		order.setExpectedDeliveryDate(Timestamp.valueOf(LocalDateTime.now().plusDays(7)));
		order.getOrderDetails().forEach(orderDetail -> {
			orderDetail.setOrder(order);
			orderDetail.setCreatedBy(customerName);
			orderDetail.setUpdatedBy(customerName);
		});
		order.setCreatedBy(customerName);
		order.setUpdatedBy(customerName);
	}

	@Override
	public OrderDTO getOrders(String orderId) {
		try {
			Integer id = Integer.parseInt(orderId.trim());
			Optional<Order> order = orderRepository.findById(id);
			if (order.isPresent()) {
				return OrderMapper.toOrderDTO(order.get());
			} else {
				throw new OrderNotFoundException(orderId);
			}
		} catch (DataAccessException e) { // Had doubt in this and it got cleared by Sumesh regarding loosing of real exception
			throw new ShoppingCartException();
		}
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateStatus(OrderDTO orderDTO) {
		try {
			orderRepository.updateOrderStatus(orderDTO.getOrderId(), orderDTO.getOrderStatusId());
			orderTrackerRepository.save(setOrderTrackerDetails(
					orderDTO, customerRepository.getCustomerName(orderDTO.getCustomerId()), orderDTO.getOrderStatusId()));
		} catch (DataAccessException e) { // Had doubt in this and it got cleared by Sumesh regarding loosing of real exception
			throw new ShoppingCartException();
		}
	}

	@Override
	public List<OrderTrackerDTO> getOrderUpdates(String orderId) {
		try {
			return orderTrackerRepository.getOrderUpdates(Integer.parseInt(orderId.trim()));
		} catch (DataAccessException e) {
			throw new ShoppingCartException();
		}
	}
}

package com.quest.shoppingcart.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_tracker")
public class OrderTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_tracker_id")
	private Integer orderTrackerid;

	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;

}

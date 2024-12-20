package com.quest.shoppingcart.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "order_status_id")
	private Integer orderStatusId;

	@ManyToOne(optional = true)
	@JoinColumn(foreignKey = @ForeignKey(name = "order_fk3"), name = "order_status_id", referencedColumnName = "order_status_id", insertable = false, updatable = false)
	private MasterOrderStatus masterOrderStatus; 

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<OrderDetail> orderDetails;
	
	@Column(name = "invoice_file_path")
	private String invoiceFilePath;

	@Column(name = "expected_delivery_date")
	private Timestamp expectedDeliveryDate;

	@Column(name = "total_price")
	private Double totalPrice;

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

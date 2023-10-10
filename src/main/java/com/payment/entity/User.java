package com.payment.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "user_detail")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "payment_amount", nullable = false)
	private String amount;

	@Column(name = "user_name", nullable = false, length = 15)
	private String userName;

	@Column(name = "user_email")
	private String userEmail;

	private String status;

	@Column(name = "payment_date_time")
	private LocalDateTime date;

	@OneToMany
	@JoinColumn(name = "payment_cart")
	private Cart cart;

}

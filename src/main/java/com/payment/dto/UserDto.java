package com.payment.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.payment.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class UserDto {

	private Long paymentId;

	private String transactionId;

	@NotEmpty
	@Size(min = 2, max = 10, message = "amount must be min :2 and max:10")
	private String amount;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String userEmail;

	@NotEmpty
	private String status;

	private LocalDateTime date;
	

}

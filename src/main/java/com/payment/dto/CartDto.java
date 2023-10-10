package com.payment.dto;

import lombok.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {

	private String cartId;

	private String cartName;

	private Date createdAt;
	
}

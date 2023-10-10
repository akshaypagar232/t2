package com.payment.service;

import com.payment.config.PageableResponse;
import com.payment.dto.UserDto;

public interface UserService {

	UserDto createPayment(UserDto userDto, String cartId);
	
	PageableResponse<UserDto> getAllPaymentDetail(int pageNumber, int pageSize, String sortBy, String sortDirection);
	
	UserDto getPaymentDetailById(Long paymentId);


}

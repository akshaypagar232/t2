package com.payment.service.impl;


import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.payment.config.Helper;
import com.payment.config.PageableResponse;
import com.payment.dto.UserDto;
import com.payment.entity.Cart;
import com.payment.entity.User;
import com.payment.exception.ResourceNotFoundException;
import com.payment.repository.CartRepo;
import com.payment.repository.UserRepo;
import com.payment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CartRepo cartRepo;

	@Override
	public UserDto createPayment(UserDto userDto, String cartId) {

		log.info("Initiated request for save the payment details in database with cartId : {}", cartId);

		String transactionId = UUID.randomUUID().toString();
		userDto.setTransactionId(transactionId);
		User user = modelMapper.map(userDto, User.class);
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException(cartId));
		user.setCart(cart);
		User save = userRepo.save(user);
		UserDto map2 = modelMapper.map(save, UserDto.class);

		log.info("Completed request for save the payment details in database with cartId : {}", cartId);
		return map2;
	}

	@Override
	public PageableResponse<UserDto> getAllPaymentDetail(int pageNumber, int pageSize, String sortBy,
			String sortDirection) {

		Sort sort = (sortDirection.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending())
				: (Sort.by(sortBy).ascending());
		Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize, sort);
		log.info("Initiated request for get all payment details in database");
		Page<User> page = userRepo.findAll(pageable);
		log.info("Completed request for get all payment details in database");
		PageableResponse<UserDto> response = Helper.getPagableResponse(page, UserDto.class);
		return response;
	}

	@Override
	public UserDto getPaymentDetailById(Long paymentId) {

		log.info("Initiated request for get all payment details in database with paymentId : {}", paymentId);
		User user = userRepo.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException(paymentId));
		UserDto userDto = modelMapper.map(user, UserDto.class);
		log.info("Completed request for get all payment details in database with paymentId : {}", paymentId);
		return userDto;
	}

}
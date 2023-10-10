package com.payment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.config.PageableResponse;
import com.payment.dto.UserDto;
import com.payment.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<UserDto> createPayment(@Valid @RequestBody UserDto userDto, @RequestParam String cartId) {

		log.info("Initiated request pass service for save the Payment details");

		UserDto user = userService.createPayment(userDto, cartId);

		log.info("Completed request for save the Payment details");

		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<PageableResponse<UserDto>> getAllPaymentDetail(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "paymentId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

		log.info("Initiated request pass service for get all the Payment details");

		PageableResponse<UserDto> allUser = userService.getAllPaymentDetail(pageNumber, pageSize, sortBy, sortDirection);

		log.info("Completed request for get all the User details");

		return new ResponseEntity<PageableResponse<UserDto>>(allUser, HttpStatus.FOUND);
	}

	@GetMapping("/id/{paymentId}")
    public ResponseEntity<UserDto> getPaymentDetailById( @PathVariable  Long paymentId
           ) {

        log.info("Initiated request pass service for get all the Payment details");

        UserDto userDto = userService.getPaymentDetailById( paymentId);

        log.info("Completed request for get all the User details");

        return new ResponseEntity<UserDto>(userDto, HttpStatus.FOUND);
    }

}
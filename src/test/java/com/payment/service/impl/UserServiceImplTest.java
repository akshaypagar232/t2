package com.payment.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.longThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.payment.config.PageableResponse;
import com.payment.dto.UserDto;
import com.payment.entity.User;
import com.payment.repository.UserRepo;
import com.payment.service.UserService;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    User user;

    @Autowired
    ModelMapper mapper;

    @BeforeEach
    public void init() {

               
        user = User.builder()
        		.transactionId("gdfjsbh")
        		.amount("468372")
        		.userName("ak")
        		.userEmail("kjdhfdfg")
				.build();

    }

    @Test
    public void createPaymentTest() {

    	String cartId = "fdsgd";
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        UserDto userDto = mapper.map(user, UserDto.class);
        UserDto user1 = userService.createPayment(userDto,cartId);
        System.out.println(user1);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(user1.getUserName(), "ak");

    }
    
    @Test
    void getAllPaymentDetailTest() {

        User u1 = User.builder()
        		.transactionId("gdfjsbh")
        		.amount("468372")
        		.userName("ak")
        		.userEmail("kjdhfdfg")
				.build();

        User u2 = User.builder()
        		.transactionId("gdfjsbh")
        		.amount("46834372")
        		.userName("akfdgdnbg")
        		.userEmail("kjdhfdfg")
				.build();


        List<User> userList = Arrays.asList(u1, u2);
        Page<User> page = new PageImpl<>(userList);
        Mockito.when(userRepo.findAll((Pageable)Mockito.any())).thenReturn(page);
        PageableResponse<UserDto> allUser = userService.getAllPaymentDetail(1,2,"userName","asc");
        Assertions.assertEquals(2,allUser.getContent().size());
        System.out.println(allUser);
    }
    
    @Test
    void getPaymentDetailByIdTest() {

    	Long paymentId =(long) 364875648;
        Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        UserDto user1 = userService.getPaymentDetailById(paymentId);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(user.getUserName(),"ak","Not found name !!!");
    }
    
    
}
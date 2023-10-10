package com.payment.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.config.PageableResponse;
import com.payment.dto.UserDto;
import com.payment.entity.User;
import com.payment.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	User user;

	@BeforeEach
	void init() {

		user = User.builder().transactionId("gdfjsbh").amount("468372").userName("hfjghbg").userEmail("kjdhfdfg")
				.build();
	}

	@Test
	void createPaymentTest() throws Exception {

		UserDto userDto = mapper.map(user, UserDto.class);
		Mockito.when(userService.createPayment(Mockito.any(), Mockito.any())).thenReturn(userDto);
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON)
						.content(convertObjectToJsonString(user)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.userName").exists());
	}

	

	@Test
	void getAllPaymentDetail() throws Exception {

		UserDto u1 = UserDto.builder().transactionId("gdfjsbh").amount("468372").userName("hfjghbg")
				.userEmail("kjdhfdfg").build();
		UserDto u2 = UserDto.builder().transactionId("gdfjsbh").amount("468372").userName("hfjghbg")
				.userEmail("kjdhfdfg").build();
		UserDto u3 = UserDto.builder().transactionId("gdfjsbh").amount("468372").userName("hfjghbg")
				.userEmail("kjdhfdfg").build();

		PageableResponse<UserDto> pageableResponse = new PageableResponse<>();
		pageableResponse.setLastPage(false);
		pageableResponse.setTotalElements(100);
		pageableResponse.setPageSize(3);
		pageableResponse.setPageNumber(0);
		pageableResponse.setContent(Arrays.asList(u1, u2, u3));
		pageableResponse.setTotalPage(10);

		Mockito.when(userService.getAllPaymentDetail(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(pageableResponse);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isFound());

	}

	@Test
    void getPaymentDetailById() throws Exception {
	
    	 UserDto userDto = UserDto.builder()
                 .transactionId("gdfjsbh")
                 .amount("468372")
                 .userName("hfjghbg")
                 .userEmail("kjdhfdfg")
                 .build();
    	
    	 Long paymentId =(long) 842534;
         Mockito.when(userService.getPaymentDetailById(Mockito.any())).thenReturn(userDto);
         this.mockMvc.perform(MockMvcRequestBuilders.get("/user/id/"+ paymentId)
                         .accept(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isFound());
     
    }

	private String convertObjectToJsonString(Object user) {

		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
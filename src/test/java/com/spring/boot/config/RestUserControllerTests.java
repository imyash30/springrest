package com.spring.boot.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
public class RestUserControllerTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	void testGetAllUsers() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/getAllUsers")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(4)))
				.andDo(print());
	}
	
	@Test
	void testCreateUser() throws Exception {
		String requestBody = "{\"firstName\":\"Rohit\",\"lastName\":\"bhosale\",\"gender\":\"Male\",\"age\":\"24\",\"email\":\"rohit@gmail.com\",\"mobile\":\"123456789\",\"address\":\"vashi\",\"dateOfBirth\":\"1995-03-30\",\"dateOfJoining\":\"2018-07-16\",\"pincode\":\"400001\"}";
	
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isBadRequest())
		        .andExpect(jsonPath("$.message", "Field validation").exists())
		        .andDo(print());
	}
	
	@Test
	void testDeleteUser() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteUser/8")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());
	}
	
	@Test
	void testUpdateUser() throws Exception {
		String requestBody = "{\"firstName\":\"Rohit\",\"lastName\":\"Bhosale\",\"gender\":\"Male\",\"age\":\"24\",\"email\":\"rohit@gmail.com\",\"mobile\":\"1234567890\",\"address\":\"vashi\",\"dateOfBirth\":\"1995-03-30\",\"dateOfJoining\":\"2018-07-16\",\"pincode\":\"400001\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put("/updateUser/4")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.firstName", "Rohit").exists())
		        .andExpect(jsonPath("$.lastName", "Bhosale").exists())
		        .andDo(print());
	}
	
	@Test
	void testGetAllUsersByName() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/getUserByNameAndPincode")
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.param("firstName", "Yash")
	            .param("lastName", "Dixit")
	            .param("pincode", "401303"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", "virar").exists())
				.andDo(print());
	}
	
	@Test
	void testGetAllUserByDobSorting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllUserByDobSorting")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(4)))
				.andDo(print());
	}

}

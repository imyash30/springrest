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

		mockMvc.perform(MockMvcRequestBuilders.get("/getAllUsersDetails")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(4)))
				.andDo(print());
	}
	
	@Test
	void testCreateUser() throws Exception {
		String requestBody = "{\"userName\":\"pawan07\",\"password\":\"Pawan07\",\"firstName\":\"Pawan\",\"lastName\":\"Shah\",\"gender\":\"Male\",\"age\":\"24\",\"email\":\"pawan@gmail.com\",\"mobile\":\"1234567890\",\"address\":\"Bhayander\",\"dateOfBirth\":\"07/11/1996\",\"pincode\":\"400001\",\"qualification\":\"BE\",\"passedYear\":\"2012\",\"certification\":\"Java\",\"companyName\":\"TCS\",\"designation\":\"Developer\",\"dateOfJoining\":\"20/06/2018\",\"yearsOfExp\":\"2\",\"technologyName\":\"java\",\"salary\":\"500000\"}";
	
		mockMvc.perform(MockMvcRequestBuilders.post("/createUser")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.message", "created successfully").exists())
		        .andDo(print());
	}
	
	@Test
	void testHardDeleteUser() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/hardDeleteUser/8")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	void testSoftDeleteUser() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/softDeleteUser/10")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", "User status changed to IsActive-N").exists())
				.andDo(print());
	}
	
	@Test
	void testUpdateUser() throws Exception {
		String requestBody = "{ \"firstName\": \"Yash\", \"lastName\": \"Dixit\", \"gender\": \"Male\", \"age\": \"24\", \"email\": \"yashdixit52@gmail.com\", \"mobile\": \"7021373502\", \"address\": \"Virar\", \"dateOfBirth\": \"30/04/1997\", \"pincode\": \"401303\" }";
		
		mockMvc.perform(MockMvcRequestBuilders.put("/updateUserDetails/1")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.firstName", "Yash").exists())
		        .andExpect(jsonPath("$.lastName", "Dixit").exists())
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
				.andExpect(jsonPath("$.*", hasSize(3)))
				.andDo(print());
	}
	
	@Test
	void testGetUserById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/getUserById/1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName", "imyash30").exists())
		        .andExpect(jsonPath("$.password", "Imyash30").exists())
				.andDo(print());
	}
	
	@Test
	void testgetDetailsByDynamicSearch() throws Exception {
		String requestBody = "[{\"key\":\"address\",\"value\":\"mumbai\"}]";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/getDetailsByDynamicSearch")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andDo(print());
	}
	
	@Test
	void testGetAllUserByDojSorting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getAllUserByDojSorting")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", hasSize(2)))
				.andDo(print());
	}

}

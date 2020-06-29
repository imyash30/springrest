package com.spring.boot.config;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.dao.MTUserDao;
import com.spring.boot.dao.UserDetailsDao;
import com.spring.boot.dto.SearchDto;
import com.spring.boot.dto.UserDetailsDto;
import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.MTUser;
import com.spring.boot.entity.UserDetails;
import com.spring.boot.exception.ResourceNotFoundException;
import com.spring.boot.service.MTUserService;
 
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestUserServiceTest {
     
    @InjectMocks
    MTUserService userService;
     
    @Mock
    MTUserDao userDao;
    
    @Mock
    UserDetailsDao userDetailsDao;
    
    @Mock
    EntityManager entityManager;
    
    @Mock
    ModelMapper modelMapper;
    
    @Mock
    Query query;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
    UserDetails userDetails;
    UserDetailsDto user1;
    UserDetailsDto user2;
    UserDetailsDto user3;
     
    
    @BeforeEach
    public void setUp() throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	userDetails = new UserDetails("Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar", sdf.parse("30/03/1997"), "401303");
    	user1 = new UserDetailsDto("1","Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar","30/04/1997", "401303");
    	user2 = new UserDetailsDto("2","Pawan", "Shah", "Male", "24", "pawan@gmail.com", "1234567890", "Bhayandar", "07/11/1996", "400003");
    	user3 = new UserDetailsDto("3","Vaibhav", "sardar", "Male", "26", "vaibhav@gmail.com", "1234567890", "mumbai", "30/03/1997", "400003");
    }
    /*@Test
    public void getUserByIDTest() throws Exception{
    	
    	SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
    	MTUser user = new MTUser();
        when(userDao.getUserByID(Long.valueOf(1))).thenReturn(user);
        
//        Mockito.doReturn(new User(Long.valueOf(1),"Yash","Dixit","Male","23","yash@gm.com","1234567890","Virar",fromUser.parse("1997/03/30"),fromUser.parse("1997/03/30"),"401303")).when(userDao.findById(Long.valueOf(1)));
        
        UserDetails userDto = userService.getUserByIdForTest(Long.valueOf(1));
         
        assertEquals("Yash", userDto.getFirstName());
        assertEquals("Dixit", userDto.getLastName());
    }*/
    
    @Test
    public void updateUserDetailsIfSuccess() throws Exception {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	final UserDetailsDto detailsDto = new UserDetailsDto("1", "Yash", "Dixit", "Male", "24", "yashdixit@gmail.com", "7021373502", "Virar", "30/03/1997", "401303");
    	final UserDetails userDetails = new UserDetails("Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar", sdf.parse("30/03/1997"), "401303");
    	when(userDetailsDao.getUserDetailsById(Long.valueOf(1))).thenReturn(userDetails);
    	when(userDetailsDao.save(userDetails)).thenReturn(userDetails);
    	
    	UserDetailsDto expectedDto = userService.updateUserDetails(Long.valueOf(1), detailsDto);
    	
    	assertEquals("Yash", expectedDto.getFirstName());
        assertEquals("Dixit", expectedDto.getLastName());
    	
    }
    
    @Test
    public void updateUserDetailsIfFailed() throws ParseException, ResourceNotFoundException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	final UserDetailsDto detailsDto = new UserDetailsDto("1", "Yash", "Dixit", "Male", "24", "yashdixit@gmail.com", "7021373502", "Virar", "30/03/1997", "401303");
    	final UserDetails userDetails = new UserDetails("Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar", sdf.parse("30/03/1997"), "401303");
    	when(userDetailsDao.getUserDetailsById(Long.valueOf(10))).thenReturn(null);
    	when(userDetailsDao.save(userDetails)).thenReturn(userDetails);
//    	when(userService.updateUserDetails(Long.valueOf(1), detailsDto)).thenThrow(new ResourceNotFoundException("Not found"));
    	
    	Exception exception = assertThrows(
    			ResourceNotFoundException.class,
    			() -> userService.updateUserDetails(Long.valueOf(1), detailsDto));

            assertTrue(exception.getMessage().contains("User not found"));
//    	assertThrows(ResourceNotFoundException.class,() -> userService.updateUserDetails(Long.valueOf(1), detailsDto));
    }
    
    @Test
    public void getUserDetailsByDynamicSearchTest() {
    	List<SearchDto> searchDtoList = new ArrayList<SearchDto>();
    	searchDtoList.add(new SearchDto("firstName", "Yash"));
    	searchDtoList.add(new SearchDto("lastName", "Dixit"));
    	
    	List<UserDetailsDto> userList = Arrays.asList(new UserDetailsDto("1", "Yash", "Dixit", "Male", "24", "yashdixit@gmail.com", "7021373502", "Virar", "30/03/1997", "401303"));
    	when(entityManager.createNativeQuery(Mockito.anyString(), Mockito.eq(UserDetails.class))).thenReturn(query);
    	when(query.getResultList()).thenReturn(userList);
    	when(modelMapper.map(userList, new TypeToken<List<UserDetailsDto>>() {}.getType())).thenReturn(userList);
    	List<UserDetailsDto> actualList = userService.getDetailsByDynamicSearch(searchDtoList);
    	
    	assertEquals(userList, actualList);
    }
     
    @Test
    public void getAllUserByDobSortingTest() throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	List<UserDetailsDto> expectedList = Arrays.asList(user1,user2,user3);
    	UserDetails u1 = new UserDetails("Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar",sdf.parse("30/04/1997"), "401303");
    	UserDetails u2 = new UserDetails("Pawan", "Shah", "Male", "24", "pawan@gmail.com", "1234567890", "Bhayandar", sdf.parse("07/11/1996"), "400003");
    	UserDetails u3 = new UserDetails("Vaibhav", "sardar", "Male", "26", "vaibhav@gmail.com", "1234567890", "mumbai", sdf.parse("30/03/1997"), "400003");
    	List<UserDetails> userList = Arrays.asList(u1,u2,u3);
    	Collections.sort(expectedList,new Comparator<UserDetailsDto>() {
			@Override
			public int compare(UserDetailsDto u1, UserDetailsDto u2) {
				// TODO Auto-generated method stub
				try {
					return sdf.parse(u1.getDateOfBirth()).compareTo(sdf.parse(u2.getDateOfBirth()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
    	when(userDetailsDao.findAllByIsActive("Y")).thenReturn(userList);
    	when(modelMapper.map(userList, new TypeToken<List<UserDetailsDto>>() {}.getType())).thenReturn(expectedList);
    	List<UserDetailsDto> actualList = userService.getAllUserByDobSorting();
    	assertEquals(expectedList, actualList);
    }
    
    /*@Test
    public void getUserByIdTest() {
    	String userJsonString = "{ \"id\": 1, \"createdby\": null, \"modifiedby\": null, \"createdon\": 1592478611000, \"modifiedon\": 1592549910000, \"isActive\": \"Y\", \"version\": 2, \"userName\": \"imyash30\", \"password\": \"Imyash30\", \"userDetails\": { \"id\": 1, \"createdby\": null, \"modifiedby\": null, \"createdon\": 1592478611000, \"modifiedon\": 1592814447000, \"isActive\": \"Y\", \"version\": 5, \"firstName\": \"Yash\", \"lastName\": \"Dixit\", \"gender\": \"Male\", \"age\": \"24\", \"email\": \"yashdixit52@gmail.com\", \"mobile\": \"7021373502\", \"address\": \"Virar\", \"dateOfBirth\": \"1997-04-29\", \"pincode\": \"401303\" }, \"userEmployementDetails\": null, \"userEducationList\": [ { \"id\": 1, \"createdby\": null, \"modifiedby\": null, \"createdon\": 1592478611000, \"modifiedon\": 1592549910000, \"isActive\": \"Y\", \"version\": 2, \"qualification\": \"BE\", \"passedYear\": 2015, \"certification\": \"Java\" }, { \"id\": 3, \"createdby\": null, \"modifiedby\": null, \"createdon\": 1592549338000, \"modifiedon\": 1592549910000, \"isActive\": \"Y\", \"version\": 1, \"qualification\": \"Diploma\", \"passedYear\": 2015, \"certification\": \"No\" } ] }";
    
    }*/
    
}

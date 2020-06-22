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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
    Query query;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
    MTUser user1;
    UserDetails userDetails;
     
    
    @Before
    public void setUp() throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	userDetails = new UserDetails("Yash", "Dixit", "Male", "24", "yashdixit52@gmail.com", "7021373502", "Virar", sdf.parse("30/03/1997"), "401303");
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
    	
    	List<UserDetails> userList = Arrays.asList(userDetails);
    	when(entityManager.createNativeQuery(Mockito.anyString(), Mockito.eq(UserDetails.class))).thenReturn(query);
    	when(query.getResultList()).thenReturn(userList);
    	List<UserDetails> actualList = userService.getDetailsByDynamicSearch(searchDtoList);
    	
    	assertEquals(userList, actualList);
    }
     
}

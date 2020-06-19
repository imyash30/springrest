/*package com.spring.boot.config;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.dao.UserDao;
import com.spring.boot.dto.UserDto;
import com.spring.boot.entity.UserDetails;
import com.spring.boot.service.UserService;
 
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestUserServiceTest {
     
    @InjectMocks
    UserService userService;
     
    @Mock
    UserDao userDao;
 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
     
     
    @Test
    public void getEmployeeByIdTest() throws Exception
    {
    	SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
    	UserDetails user = new UserDetails(Long.valueOf(1),"Yash","Dixit","Male","23","yash@gm.com","1234567890","Virar",fromUser.parse("1997/03/30"),fromUser.parse("1997/03/30"),"401303");
        when(userDao.getById(Long.valueOf(1))).thenReturn(user);
        
//        Mockito.doReturn(new User(Long.valueOf(1),"Yash","Dixit","Male","23","yash@gm.com","1234567890","Virar",fromUser.parse("1997/03/30"),fromUser.parse("1997/03/30"),"401303")).when(userDao.findById(Long.valueOf(1)));
        
        UserDetails userDto = userService.getUserByIdForTest(Long.valueOf(1));
         
        assertEquals("Yash", userDto.getFirstName());
        assertEquals("Dixit", userDto.getLastName());
    }
     
}
*/
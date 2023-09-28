package com.ruhaim.appointment.service;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.ruhaim.appointment.model.User;


public class UserServiceTest {

    private UserService userService;
    
    @BeforeEach
    void setUp() throws Exception {
        userService = UserService.getUserService();
    }
    
    @Test
    void testLoginWithInvalidCredentials() throws ClassNotFoundException, SQLException {
        String username = "John";
        String password = "9090";
        String role = "job_seeker";
       
        
        try {
            User user = userService.login(username, password, role);
            
            assertNull(user.getUsername());
            
        } catch (Throwable e) {
            fail("Exception: " + e.getMessage());
        }
    }
    
    @Disabled
    @Test
    void testLogiWithValidCredentials() throws ClassNotFoundException, SQLException {
        String username = "Ruhaim";
        String password = "9090";
        String role = "job_seeker";
        
        
        try {
            User user = userService.login(username, password, role);
            
            assertEquals(username, user.getUsername());
            
        } catch (Throwable e) {
            fail("Exception: " + e.getMessage());
        }
    }
}
package com.ruhaim.appointment.service;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ruhaim.appointment.model.User;


public class UserServiceTest {

    private UserService userService;
    
    @BeforeEach
    void setUp() throws Exception {
        userService = UserService.getUserService();
    }
    
    @Test
    void testLogin() throws ClassNotFoundException, SQLException {
        String username = "Agniiii";
        String password = "agni";
        String role = "job_seeker";
        
        
        try {
            User user = userService.login(username, password, role);
            
            assertNotNull(user);
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
            assertEquals(role, user.getRole());
            
        } catch (Throwable e) {
            fail("Exception: " + e.getMessage());
        }
    }
}
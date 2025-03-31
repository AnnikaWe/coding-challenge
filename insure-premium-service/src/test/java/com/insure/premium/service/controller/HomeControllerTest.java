package com.insure.premium.service.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.insure.premium.controller.HomeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Unit test for{@link HomeController}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 27.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

	 private MockMvc mockMvc;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	        HomeController homeController = new HomeController();
	        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	    }

	    @Test
	    void testHomeReturnsIndexView() throws Exception {
	        mockMvc.perform(get("/"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("index")); 
	    }
}

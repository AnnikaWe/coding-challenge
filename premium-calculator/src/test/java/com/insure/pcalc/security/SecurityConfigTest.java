package com.insure.pcalc.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.insure.pcalc.PremiumCalculatorApplication;
import com.insure.pcalc.service.InsurancePremiumCalculateService;

/**
 * Test for Basic Authentication 
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 26.02.2025
 */
@SpringBootTest(classes = PremiumCalculatorApplication.class)
@AutoConfigureMockMvc
public class SecurityConfigTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	 @MockBean
	    private InsurancePremiumCalculateService insurancePremiumCalculateService;

    @Test
    public void testPremiumEndpointAuthenticated() throws Exception {
        // Test the /insurance/premium endpoint with valid Basic Auth credentials
        mockMvc.perform(MockMvcRequestBuilders.post("/insurance/premium")
        		 .header(HttpHeaders.AUTHORIZATION, "Basic Y2xpZW50LTAxOmNsaWVudC1zZWNyZXQ=")// Base64 encoding of "client-01:client-secret"
                .contentType("application/json")
                .content("{\"annualMileage\": 12000, \"vehicleType\": \"Mottorad\", \"postalCode\": \"53757\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPremiumEndpointWithBasicAuth_Unauthenticated() throws Exception {
        // Test the /insurance/premium endpoint without Basic Auth (should return 401 Unauthorized)
        mockMvc.perform(MockMvcRequestBuilders.post("/insurance/premium")
                .contentType("application/json")
                .content("{\"annualMileage\": 12000, \"vehicleType\": \"Mottorad\", \"postalCode\": \"53757\"}"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    

}

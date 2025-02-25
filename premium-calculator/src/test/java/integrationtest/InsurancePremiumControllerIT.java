package integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.insure.pcalc.PremiumCalculatorApplication;

/**
 * Integration test for InsurancePremiumController
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 24.02.2025
 */
@SpringBootTest(classes = PremiumCalculatorApplication.class)
@AutoConfigureMockMvc
public class InsurancePremiumControllerIT {
	 @Autowired
	    private MockMvc mockMvc;

	    @Test
	    void testGetInsuranceQuote() throws Exception {
	        String requestJson = "{ \"annualMileage\": 15000, \"postalCode\": \"53757\", \"vehicleType\": \"Motorrad\" }";

	        mockMvc.perform(post("/insurance/premium")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(requestJson))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.premiumAmount").value(0.18))
	                .andExpect(jsonPath("$.currency").value("EUR"));
	    }
	}
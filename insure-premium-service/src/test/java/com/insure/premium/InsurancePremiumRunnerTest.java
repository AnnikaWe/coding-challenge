package com.insure.premium;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.junit.Assert.assertEquals;

import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.insure.common.model.InsurancePremiumRequest;
import com.insure.premium.service.InsurancePremiumService;

/**
 * Unit test for {@link InsurancePremiumRunner}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 24.02.2025
 */
@ExtendWith(MockitoExtension.class)
class InsurancePremiumRunnerTest {

    @Mock
    private InsurancePremiumService insurancePremiumService;

    @InjectMocks
    private InsurancePremiumRunner insurancePremiumRunner;
    
    @BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		insurancePremiumRunner = new InsurancePremiumRunner(insurancePremiumService);
	}

    @Test
    void testRunMethodCallsCalculatePremium() {
        int annualMileage = 15000;
        String vehicleType = "LKW";
        String postalCode = "53757";
        
        String simulatedUserInput = annualMileage + "\n" + vehicleType + "\n" + postalCode + "\n";
        System.setIn(new java.io.ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        insurancePremiumRunner.run();
        ArgumentCaptor<InsurancePremiumRequest> captor = ArgumentCaptor.forClass(InsurancePremiumRequest.class);
        verify(insurancePremiumService, times(1)).calculatePremium(captor.capture());

        InsurancePremiumRequest actualRequest = captor.getValue();     

        verify(insurancePremiumService, times(1)).calculatePremium(any( InsurancePremiumRequest.class));
        assertEquals(annualMileage, actualRequest.getAnnualMileage());
        assertEquals(vehicleType, actualRequest.getVehicleType());
        assertEquals(postalCode, actualRequest.getPostalCode());
    }
}
package com.insure.premium;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Test
    void testRunMethodCallsCalculatePremium() {
        int annualMileage = 15000;
        String vehicleType = "LKW";
        String postalCode = "53757";

        InsurancePremiumRequest expectedRequest = new InsurancePremiumRequest(annualMileage, vehicleType, postalCode);
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(annualMileage);
        when(mockScanner.nextLine()).thenReturn(vehicleType, postalCode);
        
        insurancePremiumRunner.run(String.valueOf(annualMileage), vehicleType, postalCode);

        verify(insurancePremiumService, times(1)).calculatePremium(expectedRequest);
    }
}
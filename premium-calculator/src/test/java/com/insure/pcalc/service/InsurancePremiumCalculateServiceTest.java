package com.insure.pcalc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import java.io.IOException;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.pcalc.data.Region;
import com.insure.pcalc.data.RegionFaktorProvider;
import com.insure.pcalc.data.RegionProvider;
import com.insure.pcalc.data.VehicleTypeFactorProvider;
import com.opencsv.exceptions.CsvValidationException;


/**
 * Unit test for {@link InsurancePremiumCalculateService}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 22.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class InsurancePremiumCalculateServiceTest {

	    @Mock
	    private RegionProvider regionProvider;

	    @InjectMocks
	    private InsurancePremiumCalculateService service;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testCalculateKilometerMileage() {
	        assertEquals(0.5, service.calculateKilometerMileage(4000));
	        assertEquals(1.0, service.calculateKilometerMileage(8000));
	        assertEquals(1.5, service.calculateKilometerMileage(15000));
	        assertEquals(2.0, service.calculateKilometerMileage(25000));
	    }

	    @Test
	    void testCalculateInsurancePremium() throws CsvValidationException, IOException {
	        Region mockRegion = new Region("Nordrhein-Westfalen", "DE", "Köln", "50667", "Innenstadt");
	        when(regionProvider.loadRegionsFromCsv(anyString())).thenReturn(List.of(mockRegion));
	        mockStatic(RegionFaktorProvider.class).when(() -> RegionFaktorProvider.getRegionFactor("Nordrhein-Westfalen")).thenReturn(1.2);
	        mockStatic(VehicleTypeFactorProvider.class)
	            .when(() -> VehicleTypeFactorProvider.getVehicleTypeFactor("Motorrad"))
	            .thenReturn(0.3);

	        InsurancePremiumRequest request = new InsurancePremiumRequest(10000, "50667", "Motorrad");
	        InsurancePremiumResponse response = service.calculateInsurancePremium(request);

	        double expectedPremium = 1.0 * 1.2 * 0.3; // mileageFactor * regionFactor * vehicleTypeFactor
	        assertEquals(expectedPremium, response.getPremiumAmount());
	    }


}
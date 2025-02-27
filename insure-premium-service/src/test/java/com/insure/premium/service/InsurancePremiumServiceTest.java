package com.insure.premium.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.entity.InsurancePremium;
import com.insure.premium.repository.InsurancePremiumRepository;

/**
 * Unit test for {@link InsurancePremiumService}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 25.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class InsurancePremiumServiceTest {

	@Mock
	private InsurancePremiumRepository repository;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private InsurancePremiumService service;

	private InsurancePremiumRequest request;
	private InsurancePremiumResponse response;

	@BeforeEach
	void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		MockitoAnnotations.openMocks(this);
		service = new InsurancePremiumService(repository);
		
		// Use reflection to inject the mock RestTemplate into the service instance
		Field restTemplateField = InsurancePremiumService.class.getDeclaredField("restTemplate");
		restTemplateField.setAccessible(true);
		restTemplateField.set(service, restTemplate); 
		

		request = new InsurancePremiumRequest(15000, "LKW", "53757");
		response = new InsurancePremiumResponse(500.0);
	}

	@Test
	void testCalculatePremium()  {
		when(restTemplate.postForEntity(anyString(), any(HttpEntity.class),
				eq(InsurancePremiumResponse.class))).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

		InsurancePremiumResponse result = service.calculatePremium(request);

		// Verify that the premium calculation was performed
		verify(repository, times(1)).save(any(InsurancePremium.class));
		assertNotNull(result);
		assertEquals(500.0, result.getPremiumAmount(), 0.0);
		assertEquals("EUR", result.getCurrency());

		// Verify the correct data was saved to the repository
		verify(repository).save(argThat(ins -> ins.getAnnualMileage() == request.getAnnualMileage()
				&& ins.getVehicleType().equals(request.getVehicleType())
				&& ins.getPostalCode().equals(request.getPostalCode())
				&& ins.getPremiumAmount() == response.getPremiumAmount()
				&& ins.getCurrency().equals(response.getCurrency())));

	}

	@Test
	void testCalculatePremiumThrowRuntimeException() {
		when(restTemplate.postForEntity(anyString(), any(HttpEntity.class),
				eq(InsurancePremiumResponse.class)))
						.thenReturn(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.calculatePremium(request);
		});

		assertTrue(exception.getMessage().contains("Error retrieving insurance premium"));
	}
}

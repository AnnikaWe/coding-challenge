package com.insure.service.integrationtest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockserver.client.MockServerClient;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import org.mockserver.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.common.model.InsurancePremiumResponse;
import com.insure.premium.InsurePremiumApplication;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest(classes = InsurePremiumApplication.class)
@AutoConfigureMockMvc
class InsuranceCalculatorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15").withDatabaseName("testdb")
            .withUsername("test").withPassword("test");

    @Container
    static MockServerContainer mockServer = new MockServerContainer("5.13.0").withExposedPorts(1080);

    @DynamicPropertySource
    static void registerPgProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        
        String mockUrl = "http://" + mockServer.getHost() + ":" + mockServer.getMappedPort(1080) + "/insurance/premium";
        registry.add("external.premium-calculator.url", () -> mockUrl);
    }
    

    @BeforeClass
    public void beforeAll() {
    	mockServer.start();
    }

    @BeforeEach
    public void before() {        
       new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getMappedPort(1080))
        .when(
            request()
                .withPath("/insurance/premium")
        )
        .respond(
            response()
            .withHeader("Content-Type", "application/json") 
            .withBody("{\"premiumAmount\": 499.99, \"currency\": \"EUR\"}")
        );
    }

    @Test
    void testCalculateInsuranceReturnsResponseViewWithModelAttributes() throws Exception {

    	mockMvc.perform(get("/api/calculate")
                .param("annualMileage", "15000")
                .param("vehicleType", "SUV")
                .param("postalCode", "12345"))
            .andExpect(status().isOk())
            .andExpect(view().name("response"))
            .andExpect(model().attribute("premiumAmount", 499.99))
            .andExpect(model().attribute("currency", "EUR"));

    }
    
    @AfterClass 
    public void stopServer() { 
        mockServer.stop();
    }
}

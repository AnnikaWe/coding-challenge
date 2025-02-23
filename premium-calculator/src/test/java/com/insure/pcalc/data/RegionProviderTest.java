package com.insure.pcalc.data;

import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

/**
 * Unit test for {@link RegionProvider}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 22.02.2025
 */
@ExtendWith(MockitoExtension.class)
public class RegionProviderTest {

	@InjectMocks
	private RegionProvider regionProvider;

	private static final String TEST_CSV_FILE = "test_regions.csv";

	@BeforeEach
	void setUp() throws IOException {
		// Create a temporary test CSV file
		File file = new File("src/main/resources/data/" + TEST_CSV_FILE);
		try (FileWriter writer = new FileWriter(file)) {
			writer.append("id,name,REGION1,type,REGION3,REGION4,POSTLEITZAHL,ORT\n");
			writer.append("1,Sample,State,Type,Country,City,12345,District\n");
		}
	}

	@Test
	void testLoadRegionsFromCsv() throws IOException, CsvValidationException {

		// Run the method
		List<Region> regions = regionProvider.loadRegionsFromCsv("data/" + TEST_CSV_FILE);

		// Assertions
		assertNotNull(regions);
		assertEquals(2, regions.size());
		assertEquals("State", regions.get(1).getState());
		assertEquals("Country", regions.get(1).getCountry());
		assertEquals("City", regions.get(1).getCity());
		assertEquals("12345", regions.get(1).getPostalCode());
		assertEquals("District", regions.get(1).getDistrict());
	}
}

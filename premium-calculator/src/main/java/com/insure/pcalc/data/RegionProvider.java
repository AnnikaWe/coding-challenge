package com.insure.pcalc.data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * Provider class for the region data.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
@Component
public class RegionProvider {

	/**
	 * Loads the region data from a CSV file.
	 * 
	 * @param filePath The path to the CSV file.
	 * @return A list of `Region` objects.
	 * @throws IOException            If an error occurs while reading the file.
	 * @throws CsvValidationException
	 */
	public List<Region> loadRegionsFromCsv(String filePath) throws IOException, CsvValidationException {
		List<Region> regions = new ArrayList<>();

		try (CSVReader reader = new CSVReader((new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath))))) {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Region region = new Region();
				region.setState(nextLine[2]); // REGION1
				region.setCountry(nextLine[4]); // REGION3
				region.setCity(nextLine[5]); // REGION4
				region.setPostalCode(nextLine[6]); // POSTLEITZAHL
				region.setDistrict(nextLine[7]); // ORT
				regions.add(region);
			}
		}
		return regions;
	}
}

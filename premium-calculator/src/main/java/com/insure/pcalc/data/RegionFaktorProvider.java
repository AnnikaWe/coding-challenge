package com.insure.pcalc.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides region factor for insurance calculation
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
public class RegionFaktorProvider {

	private static final Map<String, Double> REGION_FACTORS = new HashMap<>();

	static {
		REGION_FACTORS.put("Baden-Württemberg", Double.valueOf(0.3));
		REGION_FACTORS.put("Bayern", Double.valueOf(0.2));
		REGION_FACTORS.put("Berlin", Double.valueOf(0.4));
		REGION_FACTORS.put("Brandenburg", Double.valueOf(1.0));
		REGION_FACTORS.put("Bremen", Double.valueOf(0.5));
		REGION_FACTORS.put("Hamburg", Double.valueOf(0.3));
		REGION_FACTORS.put("Hessen", Double.valueOf(0.1));
		REGION_FACTORS.put("Mecklenburg-Vorpommern", Double.valueOf(0.3));
		REGION_FACTORS.put("Niedersachsen", Double.valueOf(0.2));
		REGION_FACTORS.put("Nordrhein-Westfalen", Double.valueOf(0.4));
		REGION_FACTORS.put("Rheinland-Pfalz", Double.valueOf(0.1));
		REGION_FACTORS.put("Sachsen", Double.valueOf(0.2));
		REGION_FACTORS.put("Sachsen-Anhalt", Double.valueOf(0.1));
		REGION_FACTORS.put("Schleswig-Holstein", Double.valueOf(0.1));
		REGION_FACTORS.put("Thüringen", Double.valueOf(0.3));
		REGION_FACTORS.put("Saarland", Double.valueOf(0.2));
	}

	public static double getRegionFactor(String region) {
		return REGION_FACTORS.getOrDefault(region, 1.0); // return default factor if no region factor is found
	}
}

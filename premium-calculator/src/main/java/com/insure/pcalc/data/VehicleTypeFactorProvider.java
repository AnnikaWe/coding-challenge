package com.insure.pcalc.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Provider class for the vehicle type factor.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
public class VehicleTypeFactorProvider {

	private static final Map<String, Double> VEHICLE_TYPE_FACTORS = new HashMap<>();

    static { 
        VEHICLE_TYPE_FACTORS.put("Motorrad",  Double.valueOf(0.3));
        VEHICLE_TYPE_FACTORS.put("Roller",  Double.valueOf(0.4));
        VEHICLE_TYPE_FACTORS.put("Elektroautos",  Double.valueOf(0.2));
        VEHICLE_TYPE_FACTORS.put("Nutzfahrzeuge",  Double.valueOf(0.5));
        VEHICLE_TYPE_FACTORS.put("LKW",  Double.valueOf(1.1));
    }
    
    public static double getVehicleTypeFactor(String region) {
		return VEHICLE_TYPE_FACTORS.getOrDefault(region, 1.0); // return default factor if no region factor is found
	}

  
  
}

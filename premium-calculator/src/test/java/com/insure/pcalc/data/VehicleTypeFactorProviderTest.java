/**
 * 
 */
package com.insure.pcalc.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link VehicleTypeFactorProvider}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 22.02.2025
 */
public class VehicleTypeFactorProviderTest {

	@Test
	void testGetVehicleTypeFactor() {
		assertEquals(0.3, VehicleTypeFactorProvider.getVehicleTypeFactor("Motorrad"));
		assertEquals(0.4, VehicleTypeFactorProvider.getVehicleTypeFactor("Roller"));
		assertEquals(0.2, VehicleTypeFactorProvider.getVehicleTypeFactor("Elektroautos"));
		assertEquals(0.5, VehicleTypeFactorProvider.getVehicleTypeFactor("Nutzfahrzeuge"));
		assertEquals(1.1, VehicleTypeFactorProvider.getVehicleTypeFactor("LKW"));
	}

	@Test
	void testGetVehicleTypeFactorForUnknownType() {
		assertEquals(1.0, VehicleTypeFactorProvider.getVehicleTypeFactor("SUV"));
	}

	@Test
	void testGetVehicleTypeFactorForNull() {
		assertEquals(1.0, VehicleTypeFactorProvider.getVehicleTypeFactor(null));
	}
}

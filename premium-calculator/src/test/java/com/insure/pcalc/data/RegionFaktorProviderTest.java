package com.insure.pcalc.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;


/**
 * Unit test for {@link RegionFaktorProvider}
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */
public class RegionFaktorProviderTest {

	@Test
	public void testGetRegionFactorValidRegion() {
		double result = RegionFaktorProvider.getRegionFactor("Nordrhein-Westfalen");
		assertThat(result, is(0.4));
	}

	@Test
	public void testGetRegionFactorReturnDefault() {
		double result = RegionFaktorProvider.getRegionFactor("");
		assertThat(result, is(1.0));

		double nullResult = RegionFaktorProvider.getRegionFactor(null);
		assertEquals(1.0, nullResult);
	}
}
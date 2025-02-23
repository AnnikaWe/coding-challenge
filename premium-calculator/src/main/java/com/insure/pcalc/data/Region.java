/**
 * 
 */
package com.insure.pcalc.data;

/**
 * Data model for region.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 21.02.2025
 */

public class Region {
	private String state; // Federal State
	private String country; // Country
	private String city; // City/Town
	private String postalCode; // Postal Code
	private String district; // District

	public Region() {
		super();
	}

	public Region(String state, String country, String city, String postalCode, String district) {
		this.state = state;
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return String.format("Region { State: %s, Country: %s, Postal Code: %s }", state, country, postalCode);
	}

}

package com.insure.premium.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class for insurance premium
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */
@Entity
@Table(name = "insurance_premium")
public class InsurancePremium {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private int annualMileage;
	private String vehicleType;
	private String postalCode;
	private double premiumAmount;
	private String currency;
	private LocalDate createdAt;

	public InsurancePremium() {
	}

	public InsurancePremium( int annualMileage, String vehicleType, String postalCode, double premiumAmount,
			String currency) {
		super();
		this.annualMileage = annualMileage;
		this.vehicleType = vehicleType;
		this.postalCode = postalCode;
		this.premiumAmount = premiumAmount;
		this.currency = currency;
		this.createdAt = LocalDate.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getAnnualMileage() {
		return annualMileage;
	}

	public void setAnnualMileage(int annualMileage) {
		this.annualMileage = annualMileage;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	

}

package com.insure.premium;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.premium.service.InsurancePremiumService;

/**
 * CommandLineRunner that takes over the user input
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 24.02.2025
 */
@Component
public class InsurancePremiumRunner implements CommandLineRunner {

	private final InsurancePremiumService insurancePremiumService;

	public InsurancePremiumRunner(InsurancePremiumService insurancePremiumService) {
		this.insurancePremiumService = insurancePremiumService;
	}

	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Bitte geben Sie die jaehrliche Kilometerleistung ein: ");
		int annualMileage = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Bitte geben Sie den Fahrzeugtyp ein: ");
		String vehicleType = scanner.nextLine();

		System.out.print("Bitte geben Sie die Postleitzahl der Region der Fahrzeugzulassung ein: ");
		String postalCode = scanner.nextLine();

		InsurancePremiumRequest request = new InsurancePremiumRequest(annualMileage, vehicleType, postalCode);
		insurancePremiumService.calculatePremium(request);
		System.out.println("Eingabe gespeichert: " + request);

		scanner.close();
	}


}

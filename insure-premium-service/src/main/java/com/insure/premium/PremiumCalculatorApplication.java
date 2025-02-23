package com.insure.premium;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.insure.common.model.InsurancePremiumRequest;
import com.insure.premium.service.InsurancePremiumService;

@SpringBootApplication
public class PremiumCalculatorApplication implements CommandLineRunner {
	private final InsurancePremiumService insurancePremiumService;

	public PremiumCalculatorApplication(InsurancePremiumService insurancePremiumService) {
		this.insurancePremiumService = insurancePremiumService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PremiumCalculatorApplication.class, args);
	}

	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Bitte geben Sie die jährliche Kilometerleistung ein: ");
		int annualMileage = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Bitte geben Sie den Fahrzeugtyp ein: ");
		String vehicleType = scanner.nextLine();

		System.out.print("Bitte geben Sie die Region der Fahrzeugzulassung ein: ");
		String postalCode = scanner.nextLine();

		InsurancePremiumRequest request = new InsurancePremiumRequest(annualMileage, vehicleType, postalCode);
		insurancePremiumService.calculatePremium(request);
		System.out.println("Eingabe gespeichert: " + request);

		scanner.close();
	}

}

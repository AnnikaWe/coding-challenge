package com.insure.premium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insure.premium.entity.InsurancePremium;

/**
 * Repository class for @InsurancePremium entity.
 * 
 * @author Annika Weisser
 * @version 1.0
 * @since 20.02.2025
 */
@Repository
public interface InsurancePremiumRepository extends JpaRepository<InsurancePremium, Long> {

}

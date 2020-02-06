package com.project.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.api.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
	
	Optional<Opportunity> findByDescriptionAndProspectusName(String description, String prospectusName);
	
}

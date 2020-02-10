package com.project.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.api.model.Opportunity;
import com.project.api.repository.OpportunityRepository;

@CrossOrigin("http://localhost:4200")
@RestController 
@RequestMapping("/opportunities")
public class OpportunityController { 
	
	@Autowired
	private OpportunityRepository opportunities;
	
	@GetMapping
	public List<Opportunity> list() {
		
		return opportunities.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Opportunity> search(@PathVariable Long id) {
		Optional<Opportunity> opportunity = opportunities.findById(id);
		
		if (opportunity.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(opportunity.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Opportunity add(@Valid @RequestBody Opportunity opportunity) {
		Optional<Opportunity> existingOpportunity = opportunities 
				.findByDescriptionAndProspectusName(opportunity.getDescription(), 
						opportunity.getProspectusName());
		
		if (existingOpportunity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"The requested opportunity already exists with the same data");
		}
		
		return opportunities.save(opportunity);
	}
	
	/** @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteStudent(@PathVariable long id) {
		opportunities.deleteById(id);
	} */
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		Optional<Opportunity> opportunity = opportunities.findById(id);
		
		if (opportunity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"The Opportunity doesn't exist or was already deleted");
		} else { 
			
			opportunities.deleteById(id);
			
		}
		
	}
	
	
}

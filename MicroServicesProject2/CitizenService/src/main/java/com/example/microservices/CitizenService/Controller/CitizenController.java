package com.example.microservices.CitizenService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.CitizenService.Entity.Citizen;
import com.example.microservices.CitizenService.Services.CitizenService;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	private CitizenService citizenService;

	// for testing
	@RequestMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}

	// to getting citizens based on VaccinationCenterId
	@RequestMapping(path = "/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		List<Citizen> listCitizens = citizenService.findByVaccinationCenterId(id);
		return new ResponseEntity<List<Citizen>>(listCitizens, HttpStatus.OK);
	}

	// adding citizen object using JPARepo built-in method
	@PostMapping(path = "/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		Citizen citizen = citizenService.saveCitizen(newCitizen);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}

	// adding citizen object using SQL insert query
	@PostMapping(path = "/addusingquery")
	public ResponseEntity<Void> addCitizenUsingQuery(@RequestBody Citizen newCitizen) {
		citizenService.addCitizenUsingQuery(newCitizen);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// inserting citizen(name) using SQL insert query
	@PostMapping(path = "/insertcitizen")
	public ResponseEntity<Void> insertCitizen(@RequestBody Citizen newCitizen) {
		citizenService.insertCitizen(newCitizen.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

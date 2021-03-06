package com.example.microservices.VaccinationCenter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.example.microservices.VaccinationCenter.Model.Citizen;
import com.example.microservices.VaccinationCenter.Model.RequiredResponse;
import com.example.microservices.VaccinationCenter.Services.VaccinationCenterService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterService vaccinationCenterService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(path = "/addcenter")
	public ResponseEntity<VaccinationCenter> addCenter(@RequestBody VaccinationCenter center) {

		VaccinationCenter centerAdded = vaccinationCenterService.saveCenter(center);
		return new ResponseEntity<VaccinationCenter>(centerAdded, HttpStatus.OK);
	}

	@GetMapping(path = "/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<RequiredResponse> getAllDatabasedOnCenterId(@PathVariable Integer id) {
		RequiredResponse response = new RequiredResponse();
		// Here we need to do two things
		// 1st get vaccination center details
		// 2nd get all citizens registered in that vaccinationCenter

		VaccinationCenter center = vaccinationCenterService.getCenterDetails(id);
		response.setCenter(center);

//		List<Citizen> citizens = restTemplate.getForObject("http://localhost:8081/citizen/id/" + id, List.class);
		/*
		 * if we deploy in cloud(eg:AWS) then localhost won't work..for that we need to
		 * use Eureka registered application name and make RestTemplate
		 * with @Loadbalanced
		 */
		List<Citizen> citizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/" + id, List.class);
		response.setCitizens(citizens);
		return new ResponseEntity<RequiredResponse>(response, HttpStatus.OK);
	}

	/*
	 * handleCitizenDownTime method will call if CitizenService is in down only
	 */
	public ResponseEntity<RequiredResponse> handleCitizenDownTime(@PathVariable Integer id) {
		RequiredResponse response = new RequiredResponse();
		VaccinationCenter center = vaccinationCenterService.getCenterDetails(id);
		response.setCenter(center);
		return new ResponseEntity<RequiredResponse>(response, HttpStatus.OK);

	}
}

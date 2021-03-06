package com.example.microservices.CitizenService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.CitizenService.Entity.Citizen;
import com.example.microservices.CitizenService.Repositories.CitizenRepository;

@Service
public class CitizenService {
	@Autowired
	private CitizenRepository citizenRepo;

	public List<Citizen> findByVaccinationCenterId(Integer id) {
		return citizenRepo.findByVaccinationCenterId(id);
	}

	public Citizen saveCitizen(Citizen newCitizen) {
		return citizenRepo.save(newCitizen);
	}

	public void addCitizenUsingQuery(Citizen newCitizen) {
		citizenRepo.addCitizenUsingQuery(newCitizen.getName(), newCitizen.getId(), newCitizen.getVaccinationCenterId());
	}

	public void insertCitizen(String name) {
		System.out.println("===>" + name);
		citizenRepo.insertCitizen(name);
	}
}

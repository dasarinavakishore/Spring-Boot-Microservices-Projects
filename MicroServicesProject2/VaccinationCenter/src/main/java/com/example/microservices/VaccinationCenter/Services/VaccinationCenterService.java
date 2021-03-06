package com.example.microservices.VaccinationCenter.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.example.microservices.VaccinationCenter.Repositories.VaccinationCenterRepository;

@Service
public class VaccinationCenterService {

	@Autowired
	private VaccinationCenterRepository vaccinationRepo;

	public VaccinationCenter saveCenter(VaccinationCenter center) {

		return vaccinationRepo.save(center);
	}

	public VaccinationCenter getCenterDetails(Integer id) {

		return vaccinationRepo.findById(id).get();
	}

}

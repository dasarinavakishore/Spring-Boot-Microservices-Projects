package com.example.microservices.CitizenService.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.microservices.CitizenService.Entity.Citizen;

@Transactional // this is required if we do custome queries
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

	public List<Citizen> findByVaccinationCenterId(Integer id);

	@Modifying
	@Query(value = "insert into citizen(id, name, vaccination_center_id) VALUES (:id,:name, :vaccinationCenterId)", nativeQuery = true)
	public void addCitizenUsingQuery(@Param("name") String name, @Param("id") int id,
			@Param("vaccinationCenterId") Integer vaccinationCenterId);

	@Modifying
	@Query(value = "insert into citizen (name) values (:name)", nativeQuery = true)
	void insertCitizen(@Param("name") String name);

}

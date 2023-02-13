package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fwolves.assignment.entity.Status;

public interface StatusRepository extends JpaRepository<Status, String>{
	@Query("SELECT s FROM Status s where s.id='CANCELED'")
	Status findByIdStatus(); 
}



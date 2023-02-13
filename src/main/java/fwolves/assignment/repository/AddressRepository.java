package fwolves.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fwolves.assignment.entity.Address;
import fwolves.assignment.entity.User;

public interface AddressRepository extends JpaRepository<Address, Long> {
	@Query("SELECT o FROM Address o WHERE o.user=:user")
	public List<Address> getByUser(@Param("user") User user);
}

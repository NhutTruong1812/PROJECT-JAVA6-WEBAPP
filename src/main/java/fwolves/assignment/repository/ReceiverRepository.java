package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwolves.assignment.entity.Address;

public interface ReceiverRepository extends JpaRepository<Address, Long> {

}

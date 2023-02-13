package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwolves.assignment.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}

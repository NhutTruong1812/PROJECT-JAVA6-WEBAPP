package fwolves.assignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fwolves.assignment.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT o FROM User o WHERE o.username=:username AND o.activated=true")
	public User getByUserName(@Param("username") String username);

	@Query("SELECT o FROM User o WHERE o.username=:username")
	public Optional<User> findByUsername(@Param("username") String username);

//	Register
	@Query("SELECT o FROM User o WHERE o.email=:email")
	public User getByEmail(@Param("email") String email);

	@Query("SELECT o FROM User o WHERE o.avatar=:avatar")
	public User getByImage(@Param("avatar") String avatar);

	@Query("SELECT o FROM User o WHERE (o.username LIKE :keyword) OR (o.fullname LIKE :keyword) OR (o.email LIKE :keyword) OR (o.phoneNumber LIKE :keyword)")
	public List<User> findByKeyword(@Param("keyword") String keyword);

}

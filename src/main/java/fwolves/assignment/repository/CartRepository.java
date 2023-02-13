package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fwolves.assignment.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	@Query("SELECT o FROM Cart o WHERE o.user.id=:userId")
	public Cart getByUserId(@Param("userId") Long userId);
}

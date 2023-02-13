package fwolves.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fwolves.assignment.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	@Query("SELECT o FROM Authority o WHERE o.role.id=:roleId")
	public List<Authority> getByRoleId(@Param("roleId") String roleId);

	@Query("SELECT o FROM Authority o WHERE o.user.id=:userId AND o.role.id=:roleId")
	public Authority getByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") String roleId);

	@Query("SELECT o FROM Authority o WHERE o.user.id=:userId")
	public List<Authority> getByUserId(@Param("userId") Long userId);
}

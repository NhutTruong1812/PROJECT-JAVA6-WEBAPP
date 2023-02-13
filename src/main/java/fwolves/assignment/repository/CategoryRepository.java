package fwolves.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fwolves.assignment.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query("select c from Category c where c.deleted='false'")
	List<Category> findAllCategory();

	@Query("SELECT o FROM Category o WHERE o.deleted=:deleted")
	public List<Category> findAll(@Param("deleted") boolean deleted);
}

package fwolves.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 

import fwolves.assignment.entity.ProductImage; 

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	@Query("SELECT o FROM ProductImage o WHERE o.product.id = ?1")
	public List<ProductImage> findAllProduct(Long id);
}

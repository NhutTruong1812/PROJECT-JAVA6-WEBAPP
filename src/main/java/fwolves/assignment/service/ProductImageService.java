package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.ProductImage;

public interface ProductImageService {

	public ResponseEntity<List<ProductImage>> get();

	public ResponseEntity<ProductImage> get(Long id);

	public ResponseEntity<ProductImage> add(ProductImage data);

	public ResponseEntity<ProductImage> update(Long id, ProductImage data);

	public ResponseEntity<ProductImage> delete(Long id);

	public ResponseEntity<List<ProductImage>> getimageByProductId(Long id);
	
	public void deleteByProduct(Long id);
}

package fwolves.assignment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Product;

public interface ProductService {

	public ResponseEntity<List<Product>> get();

	public ResponseEntity<Product> get(Long id);

	public ResponseEntity<Product> add(Product data);

	public ResponseEntity<Product> update(Long id, Product data);

	public ResponseEntity<Product> delete(Long id);

	public List<Product> findAll();

	public Page<Product> findAllProduct(Pageable pageable);

	public ResponseEntity<List<Product>> getShop(String tuKhoa, String[] danhMuc);

	public ResponseEntity<List<Product>> getCateFalse();
}

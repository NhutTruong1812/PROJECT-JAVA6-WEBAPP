package fwolves.assignment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Category;
import fwolves.assignment.entity.Product;
import fwolves.assignment.repository.CategoryRepository;
import fwolves.assignment.repository.ProductRepository;
import fwolves.assignment.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	CategoryRepository cateRepo;

	@Override
	public ResponseEntity<List<Product>> get() {
		return ResponseEntity.ok(productRepository.findAll2());
	}

	@Override
	public ResponseEntity<Product> get(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.get());
	}

	@Override
	public ResponseEntity<Product> add(Product data) {
		if (data.getDescription() == null) {
			data.setDescription("không có");
		}
		data.setCreateDate(new Date());
		Product product = productRepository.save(data);
		return ResponseEntity.ok(product);
	}

	@Override
	public ResponseEntity<Product> update(Long id, Product data) {
		Optional<Product> existOptional = productRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Product updated = productRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<Product> delete(Long id) {
		Optional<Product> existOptional = productRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAllProduct(Pageable pageable) {
		return productRepository.findAllProduct(pageable);
	}

	public ResponseEntity<List<Product>> getShop(String tuKhoa, String[] danhMuc) {
		// TODO Auto-generated method stub
		List<Product> temp = new ArrayList<>();
		List<Category> listdanhmuc = cateRepo.findAll();
		List<String> tendanhmuc = listdanhmuc.stream().map(x -> x.getId()).collect(Collectors.toList());
		String[] arrtendanhmuc = new String[tendanhmuc.size()];
		for (int i = 0; i < arrtendanhmuc.length; i++) {
			arrtendanhmuc[i] = tendanhmuc.get(i);
		}
		//
		try {
			temp = productRepository.getShop(tuKhoa == null ? "%" : "%" + tuKhoa + "%",
					danhMuc == null ? arrtendanhmuc : danhMuc);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("getShop >> " + e);
		}
		return ResponseEntity.ok(temp);
	}

	public ResponseEntity<List<Product>> getCateFalse() {
		return ResponseEntity.ok(productRepository.findAllCateFalse());
	}

}

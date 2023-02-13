package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.ProductImage;
import fwolves.assignment.repository.ProductImageRepository;
import fwolves.assignment.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;

	@Override
	public ResponseEntity<List<ProductImage>> get() {
		return ResponseEntity.ok(productImageRepository.findAll());
	}

	@Override
	public ResponseEntity<ProductImage> get(Long id) {
		Optional<ProductImage> productImage = productImageRepository.findById(id);
		if (!productImage.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productImage.get());
	}

	@Override
	public ResponseEntity<ProductImage> add(ProductImage data) {
		ProductImage productImage = productImageRepository.save(data);
		return ResponseEntity.ok(productImage);
	}

	@Override
	public ResponseEntity<ProductImage> update(Long id, ProductImage data) {
		Optional<ProductImage> existOptional = productImageRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		ProductImage updated = productImageRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<ProductImage> delete(Long id) {
		Optional<ProductImage> existOptional = productImageRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		productImageRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<ProductImage>> getimageByProductId(Long id) {
		return ResponseEntity.ok(productImageRepository.findAllProduct(id));
	}

	@Override
	public void deleteByProduct(Long id) {
		System.err.println("Xóa---------------------------");
		try {
			List<ProductImage> list = productImageRepository.findAllProduct(id);
			list.forEach(l->{
				productImageRepository.deleteById(l.getId());
				System.err.println("Xóa: "+ l.getId());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

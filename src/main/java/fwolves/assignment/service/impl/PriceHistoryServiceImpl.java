package fwolves.assignment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.PriceHistory;
import fwolves.assignment.entity.Product;
import fwolves.assignment.repository.PriceHistoryRepository;
import fwolves.assignment.service.PriceHistoryService;
import fwolves.assignment.service.ProductService;

@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {

	@Autowired
	private PriceHistoryRepository priceHistoryRepository;

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity<List<PriceHistory>> get() {
		return ResponseEntity.ok(priceHistoryRepository.findAll());
	}

	@Override
	public ResponseEntity<PriceHistory> get(Long id) {
		Optional<PriceHistory> priceHistory = priceHistoryRepository.findById(id);
		if (!priceHistory.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(priceHistory.get());
	}

	@Override
	public ResponseEntity<PriceHistory> add(PriceHistory data) {
		data.setChangeDate(new Date());
		priceHistoryRepository.save(data);
		return ResponseEntity.ok(data);
	}

	@Override
	public ResponseEntity<PriceHistory> update(Long id, PriceHistory data) {
		Optional<PriceHistory> existOptional = priceHistoryRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		PriceHistory updated = priceHistoryRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<PriceHistory> delete(Long id) {
		Optional<PriceHistory> existOptional = priceHistoryRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		priceHistoryRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<PriceHistory> getNewestByProductId(Long productId) {
		Product product = productService.get(productId).getBody();
		PriceHistory priceHistory = priceHistoryRepository.findFirst1ByProductOrderByChangeDateDesc(product);
		return ResponseEntity.ok(priceHistory);
	}

}

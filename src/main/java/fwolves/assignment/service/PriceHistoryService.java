package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.PriceHistory;

public interface PriceHistoryService {

	public ResponseEntity<List<PriceHistory>> get();

	public ResponseEntity<PriceHistory> get(Long id);

	public ResponseEntity<PriceHistory> getNewestByProductId(Long productId);

	public ResponseEntity<PriceHistory> add(PriceHistory data);

	public ResponseEntity<PriceHistory> update(Long id, PriceHistory data);

	public ResponseEntity<PriceHistory> delete(Long id);
}

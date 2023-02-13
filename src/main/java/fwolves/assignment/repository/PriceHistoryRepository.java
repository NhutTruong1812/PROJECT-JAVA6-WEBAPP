package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fwolves.assignment.entity.PriceHistory;
import fwolves.assignment.entity.Product;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {

	PriceHistory findFirst1ByProductOrderByChangeDateDesc(Product product);
}

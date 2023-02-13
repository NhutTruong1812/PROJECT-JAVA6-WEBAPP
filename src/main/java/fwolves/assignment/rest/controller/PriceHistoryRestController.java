package fwolves.assignment.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.PriceHistory;
import fwolves.assignment.service.PriceHistoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/pricehistory")
public class PriceHistoryRestController {

	@Autowired
	private PriceHistoryService priceHistoryService;

	@GetMapping("/{productId}")
	public ResponseEntity<PriceHistory> getNewestByProductId(@PathVariable("productId") Long productId) {
		return priceHistoryService.getNewestByProductId(productId);
	}
}

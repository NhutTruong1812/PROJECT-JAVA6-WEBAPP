package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.PriceHistory;
import fwolves.assignment.entity.Product;
import fwolves.assignment.entity.ProductImage;
import fwolves.assignment.service.PriceHistoryService;
import fwolves.assignment.service.ProductImageService;
import fwolves.assignment.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@Autowired 
	private ProductImageService productImageService;
	
	@Autowired
	private PriceHistoryService priceHistoryService;
	
	@GetMapping
	public ResponseEntity<List<Product>> get() {
		return productService.get();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> get(@PathVariable("id") Long id) {
		return productService.get(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
		return productService.update(id, product);
	}
	
	@GetMapping("/pro")
	public ResponseEntity<List<Product>> getCate() {
		return productService.getCateFalse();
	}
	
	@GetMapping("/img/{productId}")
	public ResponseEntity<List<ProductImage>> getimageByProductId(@PathVariable("productId") Long productId) {
		List<ProductImage> list =  productImageService.getimageByProductId(productId).getBody();
		list.forEach(l->{
			System.err.println("Name: "+ l.getName());
		});
		return productImageService.getimageByProductId(productId);
	}
	
	@PostMapping
	public Product add(@RequestBody Product product) {
		return productService.add(product).getBody();
	}
	
	@PostMapping("/imgdetails")
	public ProductImage addImg(@RequestBody ProductImage product) { 
		return productImageService.add(product).getBody();
	}
	
	@DeleteMapping("/delete/imgdetails/{productId}")
	public void deleteImg(@PathVariable("productId") Long productId) {  
		productImageService.deleteByProduct(productId);
	}
	
	@PostMapping("/p")
	public PriceHistory add(@RequestBody PriceHistory priceHistory) { 
		return priceHistoryService.add(priceHistory).getBody();
	}
}

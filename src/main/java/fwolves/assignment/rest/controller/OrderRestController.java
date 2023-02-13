package fwolves.assignment.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.Status;
import fwolves.assignment.service.OrderService;
import fwolves.assignment.service.StatusService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	@Autowired
	StatusService statusService;
	
	@GetMapping
	public List<Order> getAll(){
		return orderService.get().getBody();
	}
	
	@PutMapping("{id}")
	public Order update(@PathVariable("id") Long id, @RequestBody Order order){
		return orderService.update(id, order).getBody();
	}
	
	@PutMapping("/delete/{id}")
	public Order delete(@PathVariable("id") Long id, @RequestBody Order order) {
		Status findById = statusService.findByIdStatus();
		order.setStatus(findById);
		return orderService.update(id, order).getBody();
	}
}

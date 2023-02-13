package fwolves.assignment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.OrderHistory;
import fwolves.assignment.entity.User;
import fwolves.assignment.repository.OrderRepository;
import fwolves.assignment.service.OrderService;
import fwolves.assignment.service.StatusService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StatusService statusService;

	@Override
	public ResponseEntity<List<Order>> get() {
		return ResponseEntity.ok(orderRepository.findAll());
	}

	@Override
	public ResponseEntity<Order> get(Long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (!orderOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderOptional.get());
	}

	@Override
	public ResponseEntity<Order> add(Order data) {
		data.setCreateDate(new Date());
		data.setStatus(statusService.get("WATING").getBody());
		Order order = orderRepository.save(data);
		if (order == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(order);
	}

	@Override
	public ResponseEntity<Order> update(Long id, Order data) {
		Optional<Order> existOptional = orderRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Order updated = orderRepository.save(data);
		if (updated == null) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(updated);
	}

	@Override
	public ResponseEntity<Order> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findAllOrder(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findAllOrder(user);
	}

	@Override
	public List<Order> findAllCANCELED(User user) {
		return orderRepository.findAllCANCELED(user);
	}

	@Override
	public List<Order> findAllDELIVERED(User user) {
		return orderRepository.findAllDELIVERED(user);
	}

	@Override
	public List<Order> findAllDELIVERING(User user) {
		return orderRepository.findAllDELIVERING(user);
	}

	@Override
	public List<Order> findAllWATING(User user) {
		return orderRepository.findAllWATING(user);
	}

	@Override
	public Page<Order> findAllOrder2(User user, Pageable pageable) {
		return orderRepository.findAllOrder2(user, pageable);
	}

	@Override
	public List<OrderHistory> findSumOrder(User user, Order order) {
		return orderRepository.findAllOrder3(user, order);
	}

}

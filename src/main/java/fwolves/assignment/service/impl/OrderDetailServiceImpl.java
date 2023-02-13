package fwolves.assignment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fwolves.assignment.entity.OrderDetail;
import fwolves.assignment.repository.OrderDetailRepository;
import fwolves.assignment.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public ResponseEntity<List<OrderDetail>> get() {
		return ResponseEntity.ok(orderDetailRepository.findAll());
	}

	@Override
	public ResponseEntity<OrderDetail> get(Long id) {
		Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
		if (!orderDetailOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderDetailOptional.get());
	}

	@Override
	public ResponseEntity<OrderDetail> add(OrderDetail data) {
		OrderDetail added = orderDetailRepository.save(data);
		if (added == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(added);
	}

	@Override
	public ResponseEntity<OrderDetail> update(Long id, OrderDetail data) {
		Optional<OrderDetail> exist = orderDetailRepository.findById(id);
		if (!exist.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		data.setId(id);
		OrderDetail updateDetail = orderDetailRepository.save(data);
		return ResponseEntity.ok(updateDetail);
	}

	@Override
	public ResponseEntity<OrderDetail> delete(Long id) {
		Optional<OrderDetail> existOptional = orderDetailRepository.findById(id);
		if (!existOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		orderDetailRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}


}

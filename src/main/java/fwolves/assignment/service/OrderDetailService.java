package fwolves.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.OrderDetail;
import fwolves.assignment.entity.User;

public interface OrderDetailService {

	public ResponseEntity<List<OrderDetail>> get();

	public ResponseEntity<OrderDetail> get(Long id);

	public ResponseEntity<OrderDetail> add(OrderDetail data);

	public ResponseEntity<OrderDetail> update(Long id, OrderDetail data);

	public ResponseEntity<OrderDetail> delete(Long id);

}

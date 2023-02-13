package fwolves.assignment.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.OrderHistory;
import fwolves.assignment.entity.User;

public interface OrderService {

	public ResponseEntity<List<Order>> get();

	public ResponseEntity<Order> get(Long id);

	public ResponseEntity<Order> add(Order data);

	public ResponseEntity<Order> update(Long id, Order data);

	public ResponseEntity<Order> delete(Long id);

	public List<Order> getAll();
	
	public List<Order> findAllOrder(User user);

	public List<Order> findAllCANCELED(User user);
	
	public List<Order> findAllDELIVERED(User user);
	
	public List<Order> findAllDELIVERING(User user);
	
	public List<Order> findAllWATING(User user);
	
	public Page<Order> findAllOrder2(User user, Pageable pageable);
	
	public List<OrderHistory> findSumOrder(User user, Order order);


}

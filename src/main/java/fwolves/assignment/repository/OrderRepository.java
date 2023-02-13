package fwolves.assignment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fwolves.assignment.entity.Order;
import fwolves.assignment.entity.OrderHistory;
import fwolves.assignment.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	// lịch sử đơn hàng
	@Query("SELECT o FROM Order o where o.user=?1")
	List<Order> findAllOrder(User user);
	
	// lịch sử đơn hàng
	@Query("SELECT o FROM Order o where o.user=?1")
	Page<Order> findAllOrder2(User user, Pageable pageable);
		
	// lịch sử đơn hàng
	@Query("SELECT new OrderHistory(o.order, SUM(o.priceHistory.price * o.quantity)) FROM OrderDetail o where o.order.user=?1 and o.order=?2 group by o.order")
	List<OrderHistory> findAllOrder3(User user, Order order);
	
	// hiện thị danh sách đã hủy
	@Query("select o from Order o where o.status.id='CANCELED' and o.user=?1")
	List<Order> findAllCANCELED(User user);
	
	// hiện thị danh sách đã giao hàng
	@Query("select o from Order o where o.status.id='DELIVERED' and o.user=?1")
	List<Order> findAllDELIVERED(User user);
	
	// hiện thị danh sách Đang giao hàng
	@Query("select o from Order o where o.status.id='DELIVERING' and o.user=?1")
	List<Order> findAllDELIVERING(User user);
	
	// hiện thị danh sách Đang chờ xác nhận
	@Query("select o from Order o where o.status.id='WATING' and o.user=?1")
	List<Order> findAllWATING(User user);
}

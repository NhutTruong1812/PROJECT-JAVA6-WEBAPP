package fwolves.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fwolves.assignment.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
}

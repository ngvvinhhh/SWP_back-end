package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Order;
import vn.vvinh.be.entity.Rating;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order getOrderByID(long id);
}

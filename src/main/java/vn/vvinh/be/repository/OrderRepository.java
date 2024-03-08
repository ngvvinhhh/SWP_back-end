package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vvinh.be.entity.Order;
import vn.vvinh.be.entity.Rating;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long Id);


}

package vn.vvinh.be.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Order;
import vn.vvinh.be.entity.Rating;
import vn.vvinh.be.enums.OrderStatus;

import java.util.Date;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long Id);

    List<Order> findOrderByHostAndCreateAtBetweenAndStatus(Account account, Date startDate, Date endDate, OrderStatus orderStatus);
    List<Order> findAll();

    List<Order> findOrdersByCreateAtBetweenAndStatus(Date startDate, Date endDate, OrderStatus orderStatus);
}

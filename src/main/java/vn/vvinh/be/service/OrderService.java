package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.OrderRequestDTO;
import vn.vvinh.be.entity.*;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.enums.OrderStatus;
import vn.vvinh.be.repository.OrderRepository;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.repository.ScheduleRepository;
import vn.vvinh.be.repository.ServiceRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    // ServiceDTO methods for Order entity

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(long OrderId) {
        return orderRepository.findById(OrderId).orElse(null);
    }

    public Order saveOrder(Order Order) {
        return orderRepository.save(Order);
    }

    public void deleteOrder(long OrderId) {
        orderRepository.deleteById(OrderId);
    }

    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        List<PackageHistory> packageHistories = new ArrayList<>();
        List<ServiceHistory> serviceHistories = new ArrayList<>();
        Schedule schedule = scheduleRepository.findScheduleById(orderRequestDTO.getScheduleId());
        Order order = new Order();
        order.setStatus(OrderStatus.ORDERED);
        order.setCreateAt(new Date());
        order.setAccount(accountUtils.getCurrentAccount());
        order.setSchedule(schedule);
        order.setDate(orderRequestDTO.getDateBook());
        order.setTotal(orderRequestDTO.getTotal());

        for(Long packageId: orderRequestDTO.getPackageList()){
            Package packagea = packageRepository.findPackageById(packageId);
            PackageHistory packageHistory = new PackageHistory();
            packageHistory.setDescription(packagea.getDescription());
            packageHistory.setPicture(packagea.getPicture());
            packageHistory.setCapacity(packagea.getCapacity());
            packageHistory.setName(packagea.getName());
            packageHistory.setPrice(packagea.getPrice());
            packageHistory.setCategory(packagea.getCategory());

            packageHistories.add(packageHistory);
        }

        for(Long serviceId: orderRequestDTO.getServiceList()){
            vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(serviceId);
        ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setPrice(service.getPrice());
        serviceHistory.setPicture(service.getPicture());
        serviceHistory.setQuantity(service.getQuantity());
        serviceHistory.setServiceName(service.getServiceName());

        }
        order.setPackageHistories(packageHistories);
        order.setServiceHistories(serviceHistories);
        return order;


    }

}

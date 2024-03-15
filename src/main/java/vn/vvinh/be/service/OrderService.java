package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.OrderRequestDTO;
import vn.vvinh.be.entity.*;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.enums.OrderStatus;
import vn.vvinh.be.enums.Role;
import vn.vvinh.be.repository.*;
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

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

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
        order.setHost(schedule.getAccount());

        for(Long packageId: orderRequestDTO.getPackageList()){
            Package packagea = packageRepository.findPackageById(packageId);
            PackageHistory packageHistory = new PackageHistory();
            packageHistory.setDescription(packagea.getDescription());
            packageHistory.setPicture(packagea.getPicture());
            packageHistory.setCapacity(packagea.getCapacity());
            packageHistory.setName(packagea.getName());
            packageHistory.setPrice(packagea.getPrice());
            packageHistory.setCategory(packagea.getCategory());
            packageHistory.setOrder(order);
            packageHistories.add(packageHistory);
        }

        for(Long serviceId: orderRequestDTO.getServiceList()){
            vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(serviceId);
            if(service != null){
                ServiceHistory serviceHistory = new ServiceHistory();
                serviceHistory.setPrice(service.getPrice());
                serviceHistory.setPicture(service.getPicture());
                serviceHistory.setQuantity(service.getQuantity());
                serviceHistory.setServiceName(service.getServiceName());
                serviceHistory.setOrder(order);
                serviceHistories.add(serviceHistory);
            }
            }
            order.setPackageHistories(packageHistories);
            order.setServiceHistories(serviceHistories);
        try{
            return orderRepository.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Order acceptOrders(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(OrderStatus.ACCEPT);

        Account admin = accountRepository.findAccountByRole(Role.ADMIN);
        Account customer = order.getAccount();
        Account host = order.getHost();

        Wallet adminWallet = admin.getWallet();
        Wallet customerWallet = customer.getWallet();
        Wallet hostWallet = host.getWallet();

        if(adminWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(admin);
            admin.setWallet(wallet);
            adminWallet = walletRepository.save(wallet);
        }

        if(customerWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(customer);
            customer.setWallet(wallet);
            customerWallet = walletRepository.save(wallet);
        }

        if(hostWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(host);
            host.setWallet(wallet);
            hostWallet = walletRepository.save(wallet);
        }

        Transaction transaction = new Transaction();
        transaction.setOrder(order);
        transaction.setFrom(adminWallet);
        transaction.setTo(customerWallet);
        transaction.setCreateAt(new Date());
        transaction.setMoney(order.getTotal() * 0.95);
        transactionRepository.save(transaction);
        hostWallet.setTotal(hostWallet.getTotal() + order.getTotal() * 0.95);
        adminWallet.setTotal(adminWallet.getTotal() - order.getTotal());
        return orderRepository.save(order);
    }

    public Order refuseOrders(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(OrderStatus.REFUSE);

        Account admin = accountRepository.findAccountByRole(Role.ADMIN);
        Account customer = order.getAccount();
        Account host = order.getHost();

        Wallet adminWallet = admin.getWallet();
        Wallet customerWallet = customer.getWallet();
        Wallet hostWallet = host.getWallet();

        if(adminWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(admin);
            admin.setWallet(wallet);
            adminWallet = walletRepository.save(wallet);
        }

        if(customerWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(customer);
            customer.setWallet(wallet);
            customerWallet = walletRepository.save(wallet);
        }

        if(hostWallet == null){
            Wallet wallet = new Wallet();
            wallet.setTotal(0);
            wallet.setAccount(host);
            host.setWallet(wallet);
            hostWallet = walletRepository.save(wallet);
        }

        Transaction transaction = new Transaction();
        transaction.setOrder(order);
        transaction.setFrom(adminWallet);
        transaction.setTo(customerWallet);
        transaction.setCreateAt(new Date());
        transaction.setMoney(order.getTotal() * 0.7);
        transactionRepository.save(transaction);

        Transaction transaction2 = new Transaction();
        transaction2.setOrder(order);
        transaction2.setFrom(adminWallet);
        transaction2.setTo(hostWallet);
        transaction2.setCreateAt(new Date());
        transaction2.setMoney(order.getTotal() * 0.3);
        transactionRepository.save(transaction2);


        customerWallet.setTotal(customerWallet.getTotal() + order.getTotal() * 0.7);
        hostWallet.setTotal(hostWallet.getTotal() + order.getTotal() * 0.3);
        adminWallet.setTotal(adminWallet.getTotal() - order.getTotal());

        return orderRepository.save(order);
    }
}

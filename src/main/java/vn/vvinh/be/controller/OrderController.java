package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.OrderRequestDTO;
import vn.vvinh.be.entity.*;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.enums.OrderStatus;
import vn.vvinh.be.enums.Role;
import vn.vvinh.be.repository.*;
import vn.vvinh.be.service.OrderService;
import vn.vvinh.be.utils.AccountUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("api/order")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AccountUtils accountUtils;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/create-payment")
    public ResponseEntity createUrl(@RequestBody OrderRequestDTO orderedDTO) throws NoSuchAlgorithmException, InvalidKeyException, Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);

        Order newOrder = orderService.createOrder(orderedDTO);

        String tmnCode = "FAPWSXK2";
        String secretKey = "JGINYLOZKKTSOINMNBOCECNGZYRXHJLW";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://localhost:5173/success";

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", newOrder.getId() + "");
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + newOrder.getId() + "");
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", String.valueOf( 100 * (int) orderedDTO.getTotal()));
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "http://birthdaykids.fun/");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'

        return ResponseEntity.ok(urlBuilder.toString());
    }

    @GetMapping("/update-order")
    public Order orderSuccess(@RequestParam long orderId) {
        Order ordered = orderRepository.findOrderById(orderId);

        if(ordered.getStatus() == OrderStatus.PAID) return ordered;

        ordered.setStatus(OrderStatus.PAID);

        Account admin = accountRepository.findAccountByRole(Role.ADMIN);
        Account customer = ordered.getAccount();

        Wallet adminWallet = admin.getWallet();
        Wallet customerWallet = customer.getWallet();

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

        Transaction transaction = new Transaction();
        transaction.setOrder(ordered);
        transaction.setFrom(null);
        transaction.setTo(customerWallet);
        transaction.setCreateAt(new Date());
        transaction.setMoney(ordered.getTotal());
        transactionRepository.save(transaction);

        Transaction transaction2 = new Transaction();
        transaction2.setOrder(ordered);
        transaction2.setFrom(customerWallet);
        transaction2.setTo(adminWallet);
        transaction2.setCreateAt(new Date());
        transaction2.setMoney(ordered.getTotal());
        transactionRepository.save(transaction2);


        adminWallet.setTotal(adminWallet.getTotal() + ordered.getTotal());


        return orderRepository.save(ordered);
    }

    @GetMapping("accept")
    public Order acceptOrders(@RequestParam long orderId){
        return orderService.acceptOrders(orderId);
    }

    @GetMapping("refuse")
    public Order refuseOrders(@RequestParam long orderId){
        return orderService.refuseOrders(orderId);
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getOrders() {
        return  ResponseEntity.ok(orderService.getAllOrders());
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}

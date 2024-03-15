package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.entity.Transaction;
import vn.vvinh.be.entity.Wallet;
import vn.vvinh.be.repository.TransactionRepository;
import vn.vvinh.be.repository.WalletRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class WalletController {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountUtils accountUtils;
    @GetMapping("wallet")
    public ResponseEntity getWallet(){
        Wallet wallet = walletRepository.findWalletByAccount(accountUtils.getCurrentAccount());
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("transaction")
    public ResponseEntity getTransaction(){
        Wallet wallet = walletRepository.findWalletByAccount(accountUtils.getCurrentAccount());
        List<Transaction> transactions = transactionRepository.findTransactionsByFromOrTo(wallet, wallet);
        return ResponseEntity.ok(transactions);
    }
}

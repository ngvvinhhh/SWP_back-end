package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Transaction;
import vn.vvinh.be.entity.Wallet;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByFromOrTo(Wallet wallet1, Wallet wallet2);

}

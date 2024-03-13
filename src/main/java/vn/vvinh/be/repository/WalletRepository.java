package vn.vvinh.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vvinh.be.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {


}

package vn.vvinh.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String username);

    Account findAccountById(long id);
    Account findAccountByEmail(String email);
}

package vn.vvinh.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.enums.Role;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String username);

    Account findAccountById(long id);
    Account findAccountByEmail(String email);

    List<Account> getAllAccountByRole(Role role);

    Account findAccountByRole(Role role);
    List<Account> findAll();
}

package vn.vvinh.be.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.enums.Role;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String username);

    Account findAccountById(long id);
    Account findAccountByEmail(String email);

    List<Account> getAllAccountByRole(Role role);

    // Adjust the JPQL query to use named parameters
    @Query("SELECT a FROM Account a WHERE a.role = :role AND a.isDeleted = :isDeleted")
    List<Account> getAllNotIsDeletedAccountsByRole(@Param("role") Role role, @Param("isDeleted") boolean isDeleted);
    Account findAccountByRole(Role role);
    List<Account> findAll();
}

package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Package;

import java.util.List;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findPackagesByAccount(Account account);

    //Package findPackageById(Long Id);
}

package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Package;

import java.util.List;


@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findPackagesByAccount(Account account);

    List<Package> findPackagesByAccountId(long id);

    Package findPackageById(Long Id);

}

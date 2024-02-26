package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Package;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Long> {
    //Optional<Package> findBy(String Id);
}

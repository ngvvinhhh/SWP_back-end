package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Package;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Package, String> {
    Optional<Package> findByPartyID(String partyID);
}


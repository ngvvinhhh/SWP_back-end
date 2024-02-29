package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.entity.Service;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findServicesByPackagesContaining(Package aPackage);

    Service findServiceById(long id);



}

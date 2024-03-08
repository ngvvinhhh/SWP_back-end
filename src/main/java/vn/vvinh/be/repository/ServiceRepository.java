package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.entity.Service;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findServicesByPackagesContaining(Package aPackage);

    Service findServiceById(long id);

    List<Service> findServicesByAccountId(long id);

}

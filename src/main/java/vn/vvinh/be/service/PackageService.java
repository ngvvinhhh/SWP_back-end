package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.utils.AccountUtils;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    AccountUtils accountUtils;
    public Package postProduct(Package packages){
        Package aPackage = new Package();
        aPackage.setId(packages.getId());
        aPackage.setCapacity(packages.getCapacity());
        aPackage.setServices(packages.getServices());
        aPackage.setDescription(packages.getDescription());
        aPackage.setAccount(accountUtils.getCurrentAccount());
        Package newPackage = packageRepository.save(aPackage);
        return newPackage;
    }
}

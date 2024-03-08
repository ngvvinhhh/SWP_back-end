package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.PackageRequestDTO;
import vn.vvinh.be.dto.UpdatePackageDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.repository.ServiceRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    AccountUtils accountUtils;

    @Autowired
    ServiceRepository serviceRepository;
    public Package postParty(PackageRequestDTO packages){
        Package aPackage = new Package();
        aPackage.setId(packages.getId());
        aPackage.setName(packages.getName());
        aPackage.setCapacity(packages.getCapacity());
        aPackage.setDescription(packages.getDescription());
        aPackage.setPicture(packages.getPicture());
        aPackage.setCategory(packages.getCategory());
        aPackage.setPrice(packages.getPrice());
        aPackage.setAccount(accountUtils.getCurrentAccount());
        Package newPackage = packageRepository.save(aPackage);
        return newPackage;
    }

    public List<Package> getPackageByAccount(){
        return packageRepository.findPackagesByAccount(accountUtils.getCurrentAccount());
    }

    public List<Package> getPackageByAccountId(int hostId){
        return packageRepository.findPackagesByAccountId(hostId);
    }

    public Package updatePackage(UpdatePackageDTO updatePackageDTO, long Id){
        Package aPackage = packageRepository.findPackageById(Id);
        aPackage.setCapacity(updatePackageDTO.getCapacity());
        aPackage.setDescription(updatePackageDTO.getDescription());
        aPackage.setPicture(updatePackageDTO.getPicture());
        aPackage.setCategory(updatePackageDTO.getCategory());
        return packageRepository.save(aPackage);
    }

    public Package deletePackage(long id){

        Package aPackage = packageRepository.findPackageById(id);
        aPackage.setDeleted(true);
        return  packageRepository.save(aPackage);
    }

    public List<vn.vvinh.be.entity.Service> getServiceByPackage(long id){

        Package aPackage = packageRepository.findPackageById(id);
        List<vn.vvinh.be.entity.Service> services = serviceRepository.findServicesByPackagesContaining(aPackage);
        return services;
    }
    }


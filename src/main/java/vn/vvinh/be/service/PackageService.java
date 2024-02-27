package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.PackageRequestDTO;
import vn.vvinh.be.dto.UpdatePackageDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    AccountUtils accountUtils;
    public Package postParty(PackageRequestDTO packages){
        Package aPackage = new Package();
        aPackage.setId(packages.getId());
        aPackage.setCapacity(packages.getCapacity());
        aPackage.setDescription(packages.getDescription());
        aPackage.setAccount(accountUtils.getCurrentAccount());
        Package newPackage = packageRepository.save(aPackage);
        return newPackage;
    }

    public List<Package> getPackageByAccount(){
        return packageRepository.findPackagesByAccount(accountUtils.getCurrentAccount());
    }

    public Package updatePackage(UpdatePackageDTO updatePackageDTO, Long Id){
        Package aPackage = packageRepository.findPackageById(Id);
        aPackage.setCapacity(updatePackageDTO.getCapacity());
        aPackage.setDescription(updatePackageDTO.getDescription());
        return packageRepository.save(aPackage);
    }

    public Package deletePackage(long id){

        Package aPackage = packageRepository.findPackageById(id);
        aPackage.setDeleted(true);
        return  packageRepository.save(aPackage);

        //return   accountRepository.delete(account);

    }
    }


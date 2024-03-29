package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.ServiceRequestDTO;

import vn.vvinh.be.dto.UpdateServiceDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.entity.Schedule;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.repository.ServiceRepository;
import vn.vvinh.be.utils.AccountUtils;


import java.util.ArrayList;
import java.util.List;



@Service
public class ServiceServices  {
    @Autowired
    AccountUtils accountUtils;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    PackageRepository packageRepository;


    public vn.vvinh.be.entity.Service createService(ServiceRequestDTO serviceRequestDTO){

        vn.vvinh.be.entity.Service service = new vn.vvinh.be.entity.Service();
        if(serviceRequestDTO.getPackageId() != 0){

            Package aPackage = packageRepository.findPackageById(serviceRequestDTO.getPackageId());
            List<Package> list = new ArrayList<>();
            List<vn.vvinh.be.entity.Service> services = aPackage.getServices();
            list.add(aPackage);
            services.add(service);
            service.setPackages(list);
            aPackage.setServices(services);
            service.setAccount(accountUtils.getCurrentAccount());
        }
        service.setServiceName(serviceRequestDTO.getServiceName());
        service.setCategory(serviceRequestDTO.getCategory());
        service.setPicture(serviceRequestDTO.getPicture());
        service.setQuantity(serviceRequestDTO.getQuantity());
        service.setPrice(serviceRequestDTO.getPrice());
        vn.vvinh.be.entity.Service newService = serviceRepository.save(service);
        return newService;
    }

    public List<vn.vvinh.be.entity.Service> getServicesByPackage(long packageId){
        Package aPackage = packageRepository.findPackageById(packageId);
        List<vn.vvinh.be.entity.Service> services = serviceRepository.findServicesByPackagesContaining(aPackage);
        return services;
    }

    public List<vn.vvinh.be.entity.Service> getPackageByHostId(int hostId){
        return serviceRepository.findServicesByAccountId(hostId);
    }

    public vn.vvinh.be.entity.Service updateService(UpdateServiceDTO updateServiceDTO, long Id){
        vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(Id);
        service.setServiceName(updateServiceDTO.getServiceName());
        service.setCategory(updateServiceDTO.getCategory());
        service.setPicture(updateServiceDTO.getPicture());
        service.setQuantity(updateServiceDTO.getQuantity());
        service.setPrice(updateServiceDTO.getPrice());
        return serviceRepository.save(service);
    }


    public vn.vvinh.be.entity.Service deleteService(long id){
        vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(id);
        service.setIsDeleted(true);
        return serviceRepository.save(service);
    }
}

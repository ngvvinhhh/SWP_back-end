package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.PackageRequestDTO;
import vn.vvinh.be.dto.PackageRequestIdForServiceDTO;
import vn.vvinh.be.dto.ServiceRequestDTO;

import vn.vvinh.be.dto.UpdateServiceDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.repository.PackageRepository;
import vn.vvinh.be.repository.ServiceRepository;


import java.util.List;



@Service
public class ServiceServices  {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    PackageRepository packageRepository;


    public vn.vvinh.be.entity.Service createService(ServiceRequestDTO serviceRequestDTO){

        vn.vvinh.be.entity.Service service = new vn.vvinh.be.entity.Service();
        service.setId(serviceRequestDTO.getId());
        service.setServiceName(serviceRequestDTO.getServiceName());
        service.setCategory(serviceRequestDTO.getCategory());
        service.setPicture(serviceRequestDTO.getPicture());
        service.setQuantity(serviceRequestDTO.getQuantity());
        vn.vvinh.be.entity.Service newService = serviceRepository.save(service);
        return newService;
    }
    public List<vn.vvinh.be.entity.Service> getServiceByPackage(PackageRequestIdForServiceDTO packageRequestIdForServiceDTO){
        Package packageEntity = packageRepository.findPackageById(packageRequestIdForServiceDTO.getId());
        return serviceRepository.findServicesByPackagesContaining(packageEntity);
    }

//    public vn.vvinh.be.entity.Service getAllService(ServiceRequestDTO serviceRequestDTO){
//        vn.vvinh.be.entity.Service service = serviceRepository.findAllService();
//    }

    public vn.vvinh.be.entity.Service updateService(UpdateServiceDTO updateServiceDTO, long Id){
        vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(Id);
        service.setServiceName(updateServiceDTO.getServiceName());
        service.setCategory(updateServiceDTO.getCategory());
        service.setPicture(updateServiceDTO.getPicture());
        service.setQuantity(updateServiceDTO.getQuantity());
        return  serviceRepository.save(service);
    }


    public vn.vvinh.be.entity.Service deleteService(long id){
        vn.vvinh.be.entity.Service service = serviceRepository.findServiceById(id);
        service.setIsDeleted(true);
        return serviceRepository.save(service);
    }
}

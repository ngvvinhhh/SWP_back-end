package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Package newProduct(Package pro){
        try {
            return productRepository.save(pro);
        }catch (Exception e){
            return null;
        }
    }
}

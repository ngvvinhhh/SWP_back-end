package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.entity.Product;
import vn.vvinh.be.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product newProduct(Product pro){
        try {
            return productRepository.save(pro);
        }catch (Exception e){
            return null;
        }
    }
}

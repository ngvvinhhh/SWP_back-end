package vn.vvinh.be.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Category;
@Getter
@Setter
public class ServiceRequestDTO {
    long id;
    String serviceName;
    double price;
    int quantity;
    String picture;
    Category category;
    long packageId;
}

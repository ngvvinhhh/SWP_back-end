package vn.vvinh.be.dto;

import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Category;

@Getter
@Setter
public class UpdateServiceDTO {
    String serviceName;
    double price;
    int quantity;
    String picture;
    Category category;
}

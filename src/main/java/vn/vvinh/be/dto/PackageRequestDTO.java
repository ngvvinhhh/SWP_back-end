package vn.vvinh.be.dto;

import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Category;

import java.math.BigDecimal;

@Getter
@Setter
public class PackageRequestDTO {
    Long id;
    String name;
    int capacity;
    BigDecimal price;
    String description;
    String picture;
    Category category;
}

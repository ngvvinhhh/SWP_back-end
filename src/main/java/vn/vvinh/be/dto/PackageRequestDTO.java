package vn.vvinh.be.dto;

import lombok.Getter;
import lombok.Setter;
import vn.vvinh.be.enums.Category;

@Getter
@Setter
public class PackageRequestDTO {
    Long id;
    String name;
    int capacity;
    String description;
    String picture;
    Category category;
}

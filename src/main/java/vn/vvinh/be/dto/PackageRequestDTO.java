package vn.vvinh.be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageRequestDTO {
    Long id;
    int capacity;
    String description;
    String serviceID;

}

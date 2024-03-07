package vn.vvinh.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.vvinh.be.enums.Category;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "nvarchar(255)")
    String serviceName;
    double price;
    int quantity;
    String picture;
    Category category;
    Boolean isDeleted = false;

//    @OneToMany(mappedBy = "service")
//    ServiceOfPackage serviceOfPackage;
    @ManyToMany(mappedBy = "services", cascade = CascadeType.ALL)
        @JsonIgnore
    List<Package> packages;


    @ManyToOne()
    @JoinColumn(name = "account_id")
    Account account;


}

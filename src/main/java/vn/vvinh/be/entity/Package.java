package vn.vvinh.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int capacity;
    String description;
    String serviceID;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToMany()
    @JoinTable(
            name = "service_of_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    List<Service> services;


}



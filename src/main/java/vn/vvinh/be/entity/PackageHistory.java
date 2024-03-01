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
@Table(name = "PackageHistory")
public class PackageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String category;
    int capacity;
    String description;
    String picture;

    @ManyToMany
    @JoinTable(
            name = "service_history_of_package_history",
            joinColumns = @JoinColumn(name = "service_history_id"),
            inverseJoinColumns = @JoinColumn(name = "package_history_id")
    )
    List<ServiceHistory> serviceHistories;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

}

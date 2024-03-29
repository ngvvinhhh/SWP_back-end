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
@Table(name = "ServiceHistory")
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String serviceName;
    double price;
    int quantity;
    String picture;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToMany(mappedBy = "serviceHistories")
    List<PackageHistory> packageHistories;
}

package vn.vvinh.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String serviceHistoryID;
    String packageHistoryID;
    String scheduleID;
    String hostID;
    String packageID;
    double total;
    String time;

    @OneToMany(mappedBy = "orders")
    List<ServiceHistory> serviceHistories;
}

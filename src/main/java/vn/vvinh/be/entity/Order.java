package vn.vvinh.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import vn.vvinh.be.enums.OrderStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double total;
    Date date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ServiceHistory> serviceHistories;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    List<PackageHistory> packageHistories;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @JoinColumn(name = "host_id")
    Account host;


    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<Transaction> transactionList;

    @OneToOne()
    @JoinColumn(name = "rating_id")
    @JsonIgnore
    Rating rating;

    @ManyToOne()
    @JoinColumn(name = "schedule_id", nullable = true)
    Schedule schedule;

    @Column
    Date createAt;

    @Column
    OrderStatus status;
}

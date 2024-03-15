package vn.vvinh.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction_table")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double money = 0;
    @Column
    Date createAt;

    @ManyToOne
    @JoinColumn(name = "from_id")
    Wallet from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    Wallet to;



    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}

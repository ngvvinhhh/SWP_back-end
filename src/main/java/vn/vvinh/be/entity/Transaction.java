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
    String orderID;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    Wallet wallet;

    @Column
    Date createAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}

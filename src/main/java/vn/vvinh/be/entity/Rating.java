package vn.vvinh.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String description;
// noi voi account
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
//noi voi Order
    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;


}

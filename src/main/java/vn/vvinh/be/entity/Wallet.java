package vn.vvinh.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double total;

    @OneToMany(mappedBy = "from")
            @JsonIgnore
    List<Transaction> payFor;

    @OneToMany(mappedBy = "to")
    @JsonIgnore
    List<Transaction> receiveFrom;

    @OneToOne
    @JoinColumn(name = "account_id")
    Account account;

}

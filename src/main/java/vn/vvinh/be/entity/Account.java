package vn.vvinh.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.vvinh.be.enums.Gender;
import vn.vvinh.be.enums.Role;

import java.util.Collection;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String avatar;
    @Column(unique = true)
    String userName;
    String password;
    String fullName;
    String phoneNumber;
    @Column(unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Enumerated(EnumType.STRING)
    Role role;
    boolean isDeleted= false;
    boolean isVerify = false;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Package> packages;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Schedule> schedules;


    @OneToOne(mappedBy = "account")
    @JsonIgnore
    @JoinColumn(name = "wallet_id")
    Wallet wallet;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Rating> ratings;

    @OneToMany(mappedBy = "account")
            @JsonIgnore
    List<Order> orderList;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Service> services;

    @OneToMany(mappedBy = "host")
    @JsonIgnore
    List<Order> order;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

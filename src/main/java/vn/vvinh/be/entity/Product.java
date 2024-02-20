package vn.vvinh.be.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name =  "Product")
public class Product{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    String partyID;


    BigDecimal price;
    String location;
    LocalDate Date;

    public Product() {
    }

    public Product(String partyID, BigDecimal price, String location, LocalDate date) {
        this.partyID = partyID;
        this.price = price;
        this.location = location;
        Date = date;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "partyID='" + partyID + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", Date=" + Date +
                '}';
    }
}

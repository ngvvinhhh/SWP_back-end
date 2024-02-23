package vn.vvinh.be.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "Package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String packageID;
    String category;
    String time;
    String location;
    int capacity;
    String description;
    String serviceID;
}



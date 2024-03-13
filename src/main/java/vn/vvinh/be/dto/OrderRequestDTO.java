package vn.vvinh.be.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import vn.vvinh.be.entity.*;
import vn.vvinh.be.enums.OrderStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class OrderRequestDTO {

    List<Long> packageList;
    List<Long> serviceList;
    double total;
    long scheduleId;
    Date dateBook;
}

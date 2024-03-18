package vn.vvinh.be.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AdminDashboardDTO {

    double totalRevenue;
    List<Integer> monthlyOrder;
    List<Double> monthlyRevenue;

    public AdminDashboardDTO(double totalRevenue, List<Integer> monthlyOrder, List<Double> monthlyRevenue) {
        this.totalRevenue = totalRevenue;
        this.monthlyOrder = monthlyOrder;
        this.monthlyRevenue = monthlyRevenue;
    }
}

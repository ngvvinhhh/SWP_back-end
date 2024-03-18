package vn.vvinh.be.dto;

import lombok.Data;

import java.util.List;

@Data
public class HostDashboardDTO {

    double totalRevenue;
    List<Integer> monthlyOrder;
    List<Double> monthlyRevenue;

    public HostDashboardDTO(double totalRevenue, List<Integer> monthlyOrder, List<Double> monthlyRevenue) {
        this.totalRevenue = totalRevenue;
        this.monthlyOrder = monthlyOrder;
        this.monthlyRevenue = monthlyRevenue;
    }
}

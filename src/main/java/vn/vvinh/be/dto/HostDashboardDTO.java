package vn.vvinh.be.dto;

import lombok.Data;

import java.util.List;

@Data
public class HostDashboardDTO {

    double totalRevenue;
    List<Integer> monthlyOrder;
    List<Double> monthlyRevenue;
    int totalOrders;
    int totalGuest;
    public HostDashboardDTO(double totalRevenue, List<Integer> monthlyOrder, List<Double> monthlyRevenue, int totalOrders, int totalGuest) {
        this.totalRevenue = totalRevenue;
        this.monthlyOrder = monthlyOrder;
        this.monthlyRevenue = monthlyRevenue;
        this.totalOrders = totalOrders;
        this.totalGuest = totalGuest;
    }
}

package vn.vvinh.be.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
public class AdminDashboardDTO {

    double totalRevenue;
    List<Integer> monthlyOrder;
    List<Double> monthlyRevenue;
    int totalGuest;
    int totalHost;
    int totalOrders;

    public AdminDashboardDTO(double totalRevenue, List<Integer> monthlyOrder, List<Double> monthlyRevenue, int totalOrders, int totalHost, int totalGuest) {
        this.totalRevenue = totalRevenue;
        this.monthlyOrder = monthlyOrder;
        this.monthlyRevenue = monthlyRevenue;
        this.totalOrders = totalOrders;
        this.totalHost = totalHost;
        this.totalGuest = totalGuest;
    }
}

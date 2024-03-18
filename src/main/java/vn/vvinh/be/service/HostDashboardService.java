package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.AdminDashboardDTO;
import vn.vvinh.be.dto.HostDashboardDTO;
import vn.vvinh.be.entity.Order;
import vn.vvinh.be.enums.OrderStatus;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.repository.OrderRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class HostDashboardService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountUtils currentAccount;

    //Lay ngay dau cua moi thang
    public List<Date> returnStartDateList(){
        List<Date> StartList = new ArrayList<>();
        List<String> startString = Arrays.asList("2024-01-01", "2024-02-01",
                "2024-03-01", "2024-04-01",
                "2024-05-01", "2024-06-01",
                "2024-07-01", "2024-08-01",
                "2024-09-01", "2024-10-01",
                "2024-11-01", "2024-12-01");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 11; i++) {
            Date startDate = format.parse(startString.get(i), new ParsePosition(0));
            StartList.add(startDate);
        }
        return  StartList;
    }

    //Lay ngay cuoi cua moi thang
    public List<Date> returnEndDateList(){
        List<Date> endList = new ArrayList<>();
        List<String> endString = Arrays.asList( "2024-01-31", "2024-02-29",
                "2024-03-31", "2024-04-30",
                "2024-05-31", "2024-06-30",
                "2024-07-31", "2024-08-31",
                "2024-09-30", "2024-10-31",
                "2024-11-30", "2024-12-31" );
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 11; i++) {
            Date startDate = format.parse(endString.get(i), new ParsePosition(0));
            endList.add(startDate);
        }
        return  endList;
    }



    public HostDashboardDTO getDetail() {
        //AdminDashboardDTO adminDashboardDTO;
        //Lay danh sach ngay dau va ngay cuoi cua moi thang
        List<Date> startDate = returnStartDateList();
        List<Date> endDate = returnEndDateList();

        BigDecimal systemTotalRevenue = new BigDecimal(0);

        //TAo list de dua vao constructor
        List<Integer> monthlyOrder = new ArrayList<>();

        //Lay so luong order theo thang
        for (int i = 0; i < 11; i++) {
            List<Order> orders = orderRepository.findOrderByHostAndCreateAtBetweenAndStatus(currentAccount.getCurrentAccount(),startDate.get(i), endDate.get(i), OrderStatus.PAID);
            monthlyOrder.add(orders.size());
        }

        //Lay so luong revenue theo thang

        List<Double> monthlyRevenue = new ArrayList<>();
        double totalRevenue = 0;
        for (int i = 0; i < 11; i++) {
            List<Order> orders = orderRepository.findOrderByHostAndCreateAtBetweenAndStatus(currentAccount.getCurrentAccount(), startDate.get(i), endDate.get(i), OrderStatus.PAID);
            double total = 0;
            for (int j = 0; j < orders.size(); j++) {
                total += orders.get(j).getTotal();
            }
            monthlyRevenue.add(total);
            totalRevenue = totalRevenue + total;
        }

        //Layas dc revenue tung thang => lay dc tong revenue cua 12 thang
        return new HostDashboardDTO(totalRevenue, monthlyOrder, monthlyRevenue);
    }
}

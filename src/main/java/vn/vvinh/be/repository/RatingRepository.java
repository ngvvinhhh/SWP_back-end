package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Order;
import vn.vvinh.be.entity.Rating;

import java.util.List;
public interface RatingRepository extends JpaRepository<Rating, Long> {

    //List<Rating> FindAllRatingByAccount(long id);


//    Rating GetRatingByOrder(Order order);

}

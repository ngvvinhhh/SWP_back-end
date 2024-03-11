package vn.vvinh.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getAllScheduleByAccount(Account account);
    Schedule findScheduleById(long id);

    List<Schedule> findScheduleByAccountId(long id);

}

package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.ScheduleRequestDTO;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Schedule;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.repository.ScheduleRepository;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;
@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountUtils accountUtils;

    public Schedule postSchedule(ScheduleRequestDTO scheduleRequestDTO){
        Schedule schedule = new Schedule();
        Account account = accountUtils.getCurrentAccount();
        schedule.setAccount(account);
        schedule.setTime(scheduleRequestDTO.getTime());
        Schedule newSchedule = scheduleRepository.save(schedule);
        return newSchedule;
    }

    public List<Schedule> getScheduleByHostId(int hostId) {
        return scheduleRepository.findScheduleByAccountId(hostId);
    }



    public Schedule saveSchedule(ScheduleRequestDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setTime(scheduleDTO.getTime());
        schedule.setAccount(accountUtils.getCurrentAccount());
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(ScheduleRequestDTO scheduleRequestDTO, long id){
        Schedule schedule = scheduleRepository.findScheduleById(id);
        schedule.setTime(scheduleRequestDTO.getTime());
        return scheduleRepository.save(schedule);
    }

    public Schedule deleteSchedule(long id){
        Schedule schedule = scheduleRepository.findScheduleById(id);
        schedule.setDeleted(true);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllScheduleByOneHost( long hostID){
        return  scheduleRepository.getAllScheduleByAccount(accountRepository.findAccountById(hostID));
    }

}

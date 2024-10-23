package com.remainder.remainderApp.scheduled;

import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import com.remainder.remainderApp.service.RemainderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private RemainderRepository remainderRepository;
    @Autowired
    private RemainderService remainderService;

    @Scheduled(cron = "* * * * * *")
    public void verify(){
        List<Remainder> list = remainderRepository.findAll();

        for (Remainder item : list){

            if (item.getDateTime().isBefore(LocalDateTime.now().plusSeconds(1)) &&
                    item.getDateTime().isAfter(LocalDateTime.now().minusSeconds(1))){
                System.out.println("matched");
                remainderService.sendMail(item.getMailId(),item.getContent());
                System.out.println("Sent...");
            }
        }
    }
}

package com.remainder.remainderApp.scheduled;

import com.remainder.remainderApp.apiResponse.ApiResponse;
import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import com.remainder.remainderApp.service.RemainderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Component
public class Scheduler {

    @Autowired
    private RemainderRepository remainderRepository;
    @Autowired
    private RemainderService remainderService;
    int count = 0;
//    @Scheduled(cron = "* * * * * *")
//    public void verify(){
//        List<Remainder> list = remainderRepository.findAll();
//
//        System.out.println(count++);
//        for (Remainder item : list){
//
//            if (item.getDateTime().isBefore(LocalDateTime.now().plusSeconds(1)) && item.getDateTime().isAfter(LocalDateTime.now().minusSeconds(1))){
//                System.out.println("matched");
//                remainderService.sendMail(item.getMailId(),item.getContent());
//                System.out.println("Sent...");
//            }
//        }
//    }



    @Scheduled(cron = "0 * * * * *")
    public  void verify(){
        List<Remainder> list = remainderRepository.findByDateTimeBetween(LocalDateTime.now().minusMinutes(1),LocalDateTime.now().plusMinutes(1));
        for(Remainder item : list){
            System.out.println(item.getDateTime());
            remainderService.sendMail(item.getMailId(),item.getContent());
        }

    }



    @Scheduled(cron = "0 5 * * * *")
    public  void notifyAllRemainders(){

        LocalDateTime startOfDay = LocalDateTime.now();

        LocalDateTime endOfDay   = LocalDateTime.now().toLocalDate().plusDays(1).atStartOfDay();


        List<Remainder> remainders = remainderRepository.findByDateTimeBetween(startOfDay,endOfDay);


        Map<String,List<Remainder>> users = remainders.stream().collect(Collectors.groupingBy(Remainder::getMailId));



        users.forEach((mailId,content) ->{
            AllRemainders   allRemainders = AllRemainders.builder()
                 .mailId(mailId)
                 .content(content.toString())
                 .build();

            remainderService.sendMail(allRemainders.getMailId(),allRemainders.getContent());
            System.out.println(allRemainders.getMailId()+allRemainders.getContent());

        });


    }


}

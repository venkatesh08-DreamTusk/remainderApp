package com.remainder.remainderApp.scheduled;

import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import com.remainder.remainderApp.service.RemainderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class Scheduler {

    @Autowired
    private RemainderRepository remainderRepository;
    @Autowired
    private RemainderService remainderService;




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
    public void verify() throws InterruptedException {
        List<Remainder> list = remainderRepository.findByDateTimeBetween(LocalDateTime.now().minusMinutes(1), LocalDateTime.now().plusMinutes(1));

        List<CompletableFuture<Void>> completableFuture = list.stream().map(item->CompletableFuture.runAsync(() -> {
            try{
                remainderService.sendMail(item.getMailId(), item.getContent());
                Thread.sleep(2000);
                System.out.println("Mail sent...");

            }catch (Exception e){
                Thread.currentThread().interrupt();
                throw  new RuntimeException("Mail Not sent...",e);

            }

        })).toList();

        System.out.println("Verified...");
        }




    @Scheduled(cron = "0 0 * * * *")
    public void notifyAllRemainders() {
        LocalDateTime startOfDay = LocalDateTime.now();
        LocalDateTime endOfDay = LocalDateTime.now().toLocalDate().plusDays(1).atStartOfDay();

        List<Remainder> remainders = remainderRepository.findByDateTimeBetween(startOfDay, endOfDay);


        Map<String, List<Remainder>> users = remainders.stream().collect(Collectors.groupingBy(Remainder::getMailId));

        users.forEach((mailId, content) -> {

            remainderService.sendMail(mailId, content.toString());
            System.out.println("Mail Sent...");

        });
    }


    }







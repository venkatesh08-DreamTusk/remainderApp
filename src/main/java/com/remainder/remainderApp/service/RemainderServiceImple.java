package com.remainder.remainderApp.service;

import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RemainderServiceImple implements  RemainderService{

    @Autowired
    private RemainderRepository remainderRepository;

    @Autowired
    private MailSender mailSender;


    @Override
    public Remainder addRemainder(Remainder remainder) {
        return remainderRepository.save(remainder);
    }

    @Override
    @Async
    public void sendMail(String mailId, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailId);
        message.setSubject("Just Remainder For You ");
        message.setText(content);


        mailSender.send(message);
    }

    @Override
    public List<Remainder> getAllRemainders() throws Exception {
        List<Remainder> remainders =  remainderRepository.findAll();


        if (!remainders.isEmpty()){
            return remainders;
        }else {
            throw new Exception("There is No DATA");
        }
    }

    @Override
    public void removeRemainder(Long id) throws Exception {
        Optional<Remainder> remainder = remainderRepository.findById(id);

        if(remainder.isPresent()){
            remainderRepository.deleteById(id);
        }else {
            throw new Exception("Id Not Found");
        }

    }


}

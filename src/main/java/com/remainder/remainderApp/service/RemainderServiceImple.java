package com.remainder.remainderApp.service;

import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


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
    public void sendMail(String mailId, String content) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailId);
        message.setSubject("Just Remainder For You ");
        message.setText(content);

        mailSender.send(message);
    }


}

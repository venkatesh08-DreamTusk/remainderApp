package com.remainder.remainderApp.service;

import com.remainder.remainderApp.entity.Remainder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RemainderService {
    Remainder addRemainder(Remainder remainder);

    void sendMail(String mailId, String content);

    List<Remainder> getAllRemainders() throws  Exception;

    void removeRemainder(Long id) throws Exception;

}

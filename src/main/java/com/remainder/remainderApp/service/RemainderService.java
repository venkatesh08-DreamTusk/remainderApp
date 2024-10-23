package com.remainder.remainderApp.service;

import com.remainder.remainderApp.entity.Remainder;
import java.util.List;

public interface RemainderService {
    Remainder addRemainder(Remainder remainder);

    void sendMail(String mailId, String content);
}

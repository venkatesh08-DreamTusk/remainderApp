package com.remainder.remainderApp.service;

import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.repository.RemainderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemainderServiceImple implements  RemainderService{

    @Autowired
    private RemainderRepository remainderRepository;

    @Override
    public Remainder addRemainder(Remainder remainder) {
        return null;
    }
}

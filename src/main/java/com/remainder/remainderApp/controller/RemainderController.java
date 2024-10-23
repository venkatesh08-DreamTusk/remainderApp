package com.remainder.remainderApp.controller;

import com.remainder.remainderApp.apiResponse.ApiResponse;
import com.remainder.remainderApp.dto.RemainderDto;
import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.service.RemainderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/remainder")
public class RemainderController {

    @Autowired
    private RemainderService remainderService;

    @PostMapping()
    public ApiResponse<Remainder> addContent(@Valid  @RequestBody RemainderDto remainderDto){


        Remainder remainder = Remainder.builder()
                .content(remainderDto.getContent())
                .mailId(remainderDto.getMailId())
                .dateTime(remainderDto.getDateTime())
                .build();

        Remainder newRemainder = remainderService.addRemainder(remainder);

        return ApiResponse.<Remainder>builder()
                .status(true)
                .data(newRemainder)
                .error(null)
                .build();

    }
}

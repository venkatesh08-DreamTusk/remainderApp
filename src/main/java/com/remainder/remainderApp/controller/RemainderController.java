package com.remainder.remainderApp.controller;

import com.remainder.remainderApp.apiResponse.ApiResponse;
import com.remainder.remainderApp.dto.RemainderDto;
import com.remainder.remainderApp.entity.Remainder;
import com.remainder.remainderApp.service.RemainderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remainder")
public class RemainderController {

    @Autowired
    private RemainderService remainderService;

    @PostMapping
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

    @GetMapping
    public  ApiResponse<List<Remainder>> getAllRemainders() throws  Exception{
        List<Remainder> remainders = remainderService.getAllRemainders();
        return ApiResponse.<List<Remainder>>builder()
                .status(true)
                .data(remainders)
                .error(null)
                .build();
    }

    @DeleteMapping("/{id}")
    public String removeRemainder(@PathVariable Long id) throws Exception {
        remainderService.removeRemainder(id);
        return "Deleted Successfully";
    }

}

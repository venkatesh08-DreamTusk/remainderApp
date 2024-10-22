package com.remainder.remainderApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemainderDto {

    @Email(message = "Email id Can't be Empty")
    private String mailId;

    @NotBlank(message = "Content Can't be Empty")
    private  String content;

    @NotNull(message = "Date and Time Can't be Null")
    private LocalDateTime dateTime;
}

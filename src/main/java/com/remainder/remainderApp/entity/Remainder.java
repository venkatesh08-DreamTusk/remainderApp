package com.remainder.remainderApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "remainders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remainder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String mailId;

    private String content;

    private LocalDateTime dateTime;
}

package com.example.alkemy.disney.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MessageException {


    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String date;
    private String status;
    private List<String> message;
}

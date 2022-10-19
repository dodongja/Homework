package com.example.demo.controller.yMaker.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class BoardRequest {
    private int boardNo;
    private String id;
    private String name;
    private String gender;
    private String country;
    private String city;
    private String startDate;
    private String endDate;
}

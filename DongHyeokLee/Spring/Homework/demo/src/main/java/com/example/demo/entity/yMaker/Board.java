package com.example.demo.entity.yMaker;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class Board {
    private String id;
    private String name;
    private String gender;
    private String country;
    private String city;
    private Date regDate;

}

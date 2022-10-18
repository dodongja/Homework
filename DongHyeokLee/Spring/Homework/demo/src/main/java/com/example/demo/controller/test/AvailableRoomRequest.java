package com.example.demo.controller.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableRoomRequest {
    List<String> settingDates = new ArrayList<>();
    List<String> roomSetting = new ArrayList<>();
    List<Integer> settingPrice = new ArrayList<>();
    List<Integer> settingAvailableRoom = new ArrayList<>();


}

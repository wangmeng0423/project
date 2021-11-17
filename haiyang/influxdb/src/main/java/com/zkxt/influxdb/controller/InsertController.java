package com.zkxt.influxdb.controller;


import com.zkxt.influxdb.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class InsertController {

    @Autowired
    private InsertService insertService;


    @RequestMapping("/test1")
    public void insertDataController(@RequestParam String measurement, @RequestBody Map<String, Object> fields) {
        insertService.insertData(measurement,fields);
        System.out.println("================插入数据成功=============");
    }
}

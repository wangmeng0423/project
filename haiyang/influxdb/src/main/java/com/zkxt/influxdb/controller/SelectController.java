package com.zkxt.influxdb.controller;


import com.influxdb.query.FluxTable;
import com.zkxt.influxdb.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectController {
    @Autowired
    private SelectService selectService;

    @GetMapping("/test2")
    public List<FluxTable> selectDataController() {

        System.out.println("===========查询数据完成=============");
        return selectService.selectData();
    }
}

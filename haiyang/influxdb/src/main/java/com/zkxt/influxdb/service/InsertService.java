package com.zkxt.influxdb.service;


import java.util.Map;

/**
 * 插入数据接口
 */
public interface InsertService {

    void insertData(String measurement,Map<String, Object> fields);
}

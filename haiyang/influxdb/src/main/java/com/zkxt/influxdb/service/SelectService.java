package com.zkxt.influxdb.service;

import com.influxdb.query.FluxTable;

import java.util.List;

/**
 * 查询数据接口
 */
public interface SelectService {

    List<FluxTable> selectData();


}

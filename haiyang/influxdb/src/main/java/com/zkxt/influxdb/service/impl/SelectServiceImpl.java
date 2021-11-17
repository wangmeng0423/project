package com.zkxt.influxdb.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxTable;
import com.zkxt.influxdb.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SelectServiceImpl implements SelectService {
    @Value("${spring.influx.org:''}")
    private String org;

    @Value("${spring.influx.bucket:''}")
    private String bucket;

    @Autowired
    private InfluxDBClient influxDBClient;

    public List<FluxTable> selectData() {

        System.out.println("==============开始查询数据================");
        String sql = "from(bucket: \"%s\") |> range(start: 0)";
        sql +="  |> filter(fn: (r) => r._measurement == \"%s\" and";
        sql +="  r._field == \"%s\")";
        sql +="  |> yield()";
        String flux = String.format(sql, bucket,"mq","name");
        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux,org);
        System.out.println(tables.toString());
        return tables;
    }
}


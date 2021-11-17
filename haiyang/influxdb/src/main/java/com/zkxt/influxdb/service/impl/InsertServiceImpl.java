package com.zkxt.influxdb.service.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.zkxt.influxdb.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
@Service
public class InsertServiceImpl implements InsertService {

    @Autowired
    private InfluxDBClient influxDBClient;

    @Value("${spring.influx.org:''}")
    private String org;
    @Value("${spring.influx.bucket:''}")
    private String bucket;

    public void insertData(String measurement, Map<String, Object> fields) {

        System.out.println("=============开始插入数据=============");
        WriteOptions writeOptions = WriteOptions.builder()
                .batchSize(5000)
                .flushInterval(1000)
                .bufferLimit(10000)
                .jitterInterval(1000)
                .retryInterval(5000)
                .build();
        try (WriteApi writeApi = influxDBClient.getWriteApi(writeOptions)) {
            String address = fields.get("address").toString();
            fields.remove("address");
            Point point = Point
                    .measurement(measurement)
                    .addTag("address", address)
                    .addFields(fields)
                    .time(Instant.now(), WritePrecision.NS);
            writeApi.writePoint(bucket, org, point);
        }
    }
}


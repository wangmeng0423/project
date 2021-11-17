package com.zkxt.influxdb.config;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * influx配置类
 */
@Configuration
public class InfluxDbConfig {

    @Value("${spring.influx.token:''}")
    private String token;
    @Value("${spring.influx.url:''}")
    private String url;
    @Bean
    public InfluxDBClient influxDBClient() {

        System.out.println("=============="+token);
        System.out.println("=============="+url);
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token.toCharArray());
        influxDBClient.setLogLevel(LogLevel.BASIC);
        return influxDBClient;

    }
}


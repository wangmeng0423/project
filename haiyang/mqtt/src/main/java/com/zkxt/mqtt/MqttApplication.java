package com.zkxt.mqtt;

import com.zkxt.mqtt.config.MqttConfiguration;
import com.zkxt.mqtt.config.MqttPushClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.zkxt.influxdb")})
public class MqttApplication implements ApplicationRunner {
    //读取mqtt配置
    @Autowired
    private MqttConfiguration mqttConfiguration;

    Logger log= LoggerFactory.getLogger(getClass());
    public static void main(String[] args) {
        SpringApplication.run(MqttApplication.class, args);
    }

    /**
     * mqtt 初始化
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(true){
            if (log.isInfoEnabled()){
                log.info("===============>>>Mqtt is run starting:<<<==================");
            }
            MqttPushClient mqttPushClient = new MqttPushClient();
            mqttPushClient.connect(mqttConfiguration);
        }
    }




}

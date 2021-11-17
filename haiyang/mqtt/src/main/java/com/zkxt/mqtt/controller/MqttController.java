package com.zkxt.mqtt.controller;

import com.zkxt.mqtt.config.MqttPushClient;
import com.zkxt.mqtt.config.MqttSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/mqtt")
public class MqttController {
    Logger log= LoggerFactory.getLogger(getClass());
    //发送逻辑
    @Autowired
    private MqttSender mqttSender;

    //订阅逻辑
    @Autowired
    private MqttPushClient mqttPushClient;
    @ApiOperation(value = "")
    @RequestMapping("/sendmqtt")
    public String sendmqtt(String TOPIC1,String JSON){
//        String TOPIC1="testtest1";
//        String JSON = "{'gender':'man','hobby':'girl'}";
//        log.info("    本机主题:"+TOPIC1+" 发送数据为:"+ JSONObject.toJSONString(JSON));
        log.info("    本机主题:"+TOPIC1+" 发送数据为:"+ JSON);
        mqttSender.send(TOPIC1, JSON);
        log.info("=================发送结束=====================");
        return "发送结束";
    }

    @RequestMapping("/subscriptionmqtt")
    public String subscriptionmqtt(String TOPIC1,int Qos){
//        int Qos=1;
//        String TOPIC1="testtest1";
        String[] topics={TOPIC1};
        int[] qos={Qos};
        mqttPushClient.subscribe(topics,qos);
        return "订阅主题";
    }
}

